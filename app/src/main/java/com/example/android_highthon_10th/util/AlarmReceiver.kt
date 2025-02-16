package com.example.android_highthon_10th.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.android_highthon_10th.presentation.alarm.AlarmActivity

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val activityIntent = Intent(it, AlarmActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            it.startActivity(activityIntent)
        }
    }
}
