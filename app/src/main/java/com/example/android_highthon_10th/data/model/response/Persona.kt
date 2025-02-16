package com.example.android_highthon_10th.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Persona(
    val createdAt: String,
    val description: String,
    val name: String,
    val profileImage: String,
    val uuid: String
)