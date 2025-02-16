package com.example.android_highthon_10th.data.model.response

import kotlinx.serialization.Serializable

data class JwtTokenResponse(
    val accessToken: String,
    val refreshToken: String
)