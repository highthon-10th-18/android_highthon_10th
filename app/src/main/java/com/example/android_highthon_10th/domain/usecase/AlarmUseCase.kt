package com.example.android_highthon_10th.domain.usecase

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import com.example.android_highthon_10th.domain.repository.AlarmRepository
import com.example.android_highthon_10th.util.AlarmReceiver
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

class AlarmUseCase @Inject constructor(
    private val repository: AlarmRepository
) {
    suspend fun getAlarms(): List<AlarmResponse> {
        return repository.getAlarms()
    }

    suspend fun createAlarm(context: Context, body: AlarmBody): AlarmResponse {
        val response = repository.createAlarm(body)

        val time = calculateTriggerTimeMillis(response.hour, response.minute)
        setAlarm(context, time, response.id)

        Log.d("확인124321341234", time.toString())
        return response
    }

    suspend fun editAlarm(context: Context, uuid: String, body: AlarmEditBody): AlarmResponse {
        val response = repository.editAlarm(uuid, body)
        modifyAlarm(context, response.id, calculateTriggerTimeMillis(response.hour, response.minute))

        return response
    }

    suspend fun deleteAlarm(context: Context, uuid: String) {
        val getAlarms = getAlarms()

        val id = getAlarms.first { it.uuid == uuid}.id
        cancelAlarm(context, id)

        repository.deleteAlarm(uuid)
    }

    private fun calculateTriggerTimeMillis(hour: Int, minute: Int): Long {
        val timeZone = TimeZone.getTimeZone("Asia/Seoul")
        val currentTime = Calendar.getInstance(timeZone)

        val targetTimeCalendar = Calendar.getInstance(timeZone)
        targetTimeCalendar.set(Calendar.HOUR_OF_DAY, hour)
        targetTimeCalendar.set(Calendar.MINUTE, minute)
        targetTimeCalendar.set(Calendar.SECOND, 0)
        targetTimeCalendar.set(Calendar.MILLISECOND, 0)

        if (targetTimeCalendar.before(currentTime)) {
            targetTimeCalendar.add(Calendar.DATE, 1)
        }

        return targetTimeCalendar.timeInMillis
    }

    private fun setAlarm(context: Context, triggerTimeMillis: Long, alarmId: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                if (!alarmManager.canScheduleExactAlarms()) {
                    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    context.startActivity(intent)
                    return
                }
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent)
            Log.d("Alarm", "정확한 알람이 설정되었습니다: $triggerTimeMillis")
        } catch (e: SecurityException) {
            Log.e("Alarm", "알람 설정 중 보안 예외 발생: ${e.message}")
        }
    }

    private fun cancelAlarm(context: Context, alarmId: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun modifyAlarm(context: Context, alarmId: Int, newTriggerTimeMillis: Long) {
        cancelAlarm(context, alarmId)
        setAlarm(context, newTriggerTimeMillis, alarmId)
    }

}