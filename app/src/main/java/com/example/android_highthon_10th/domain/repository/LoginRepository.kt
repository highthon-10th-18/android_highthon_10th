package com.example.android_highthon_10th.domain.repository

import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse


interface LoginRepository {
    suspend fun loginRequest(body: LoginBody): JwtTokenResponse
}
