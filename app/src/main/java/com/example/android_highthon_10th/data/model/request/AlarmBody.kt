package com.example.android_highthon_10th.data.model.request

data class AlarmBody(
    val hour: Int,
    val minute: Int,
    val personaUUID: String,
    val repeatDays: List<Int>
)