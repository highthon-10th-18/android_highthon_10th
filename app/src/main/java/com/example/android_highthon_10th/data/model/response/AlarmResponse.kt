package com.example.android_highthon_10th.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AlarmResponse(
    val id: Int,
    val hour: Int,
    val isActivated: Boolean,
    val minute: Int,
    val persona: Persona,
    val repeatDays: List<Int>,
    val uuid: String
)