package com.example.android_highthon_10th.data.datasource.source

import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.RefreshTokenBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse

interface AuthRemoteDataSource {
    suspend fun postLogin(body: LoginBody): JwtTokenResponse
    suspend fun postSignUp(body: SignUpBody): JwtTokenResponse
    suspend fun refresh(body: RefreshTokenBody): JwtTokenResponse
}