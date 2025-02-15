package com.example.android_highthon_10th.data.datasource

import com.example.android_highthon_10th.data.api.AuthApi
import com.example.android_highthon_10th.data.datasource.source.AuthRemoteDataSource
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.RefreshTokenBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: AuthApi
): AuthRemoteDataSource {

    override suspend fun postLogin(body: LoginBody): JwtTokenResponse {
        return api.login(body)
    }

    override suspend fun postSignUp(body: SignUpBody): JwtTokenResponse {
        return api.signUp(body)
    }

    override suspend fun refresh(body: RefreshTokenBody): JwtTokenResponse {
        return api.refresh(body)
    }

}