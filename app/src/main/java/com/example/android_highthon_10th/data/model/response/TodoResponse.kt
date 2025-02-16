package com.example.android_highthon_10th.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TodoResponse(
    val isDone: Boolean,
    val name: String,
    val persona: Persona,
    val targetDate: String,
    val uuid: String
)