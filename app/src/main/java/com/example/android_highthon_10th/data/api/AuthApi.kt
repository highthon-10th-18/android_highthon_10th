package com.example.android_highthon_10th.data.api

import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.RefreshTokenBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginBody): JwtTokenResponse

    @POST("auth/refresh")
    suspend fun signUp(@Body body: SignUpBody): JwtTokenResponse

    @POST("auth/register")
    suspend fun refresh(@Body body: RefreshTokenBody): JwtTokenResponse
}