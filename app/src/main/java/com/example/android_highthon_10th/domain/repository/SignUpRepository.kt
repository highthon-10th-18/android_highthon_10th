package com.example.android_highthon_10th.domain.repository

import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse


interface SignUpRepository {
    suspend fun signUpRequest(body: SignUpBody): JwtTokenResponse
}
