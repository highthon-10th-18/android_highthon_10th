package com.example.android_highthon_10th.presentation

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat.startForeground
import androidx.core.content.ContextCompat
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.util.MyForegroundService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!canScheduleExactAlarms(this)) {
            openSettingsForExactAlarms(this)
        } else {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    Color.TRANSPARENT, Color.TRANSPARENT
                ),
                navigationBarStyle = SystemBarStyle.auto(
                    Color.TRANSPARENT, Color.TRANSPARENT
                )
            )
            setContent {
                AppTheme {
                    MainNavHost()
                }
            }
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.FOREGROUND_SERVICE_DATA_SYNC)
            == PackageManager.PERMISSION_GRANTED) {
            // 권한이 있으면 서비스 시작
            startForegroundService(Intent(this, MyForegroundService::class.java))
        } else {
            // 권한이 없으면 사용자에게 알림
            Toast.makeText(this, "데이터 동기화를 위한 권한이 필요합니다.", Toast.LENGTH_LONG).show()
            // 필요한 경우 사용자를 설정 화면으로 안내
        }
//
//        createNotificationChannel()
//        startForegroundService()

    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            "0",
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    private fun openSettingsForExactAlarms(context: Context) {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        } else {
            null
        }
        intent?.let { context.startActivity(it) }
    }


    private fun canScheduleExactAlarms(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

}
