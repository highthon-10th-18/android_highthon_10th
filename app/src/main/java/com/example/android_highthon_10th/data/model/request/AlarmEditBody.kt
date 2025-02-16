package com.example.android_highthon_10th.data.model.request

data class AlarmEditBody(
    val hour: Int,
    val isActivated: Boolean,
    val minute: Int,
    val personaUUID: String,
    val repeatDays: List<Int>
)