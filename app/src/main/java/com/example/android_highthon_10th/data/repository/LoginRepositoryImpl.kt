package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.AuthRemoteDataSource
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse
import com.example.android_highthon_10th.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
): LoginRepository {
    override suspend fun loginRequest(body: LoginBody): JwtTokenResponse {
        return remote.postLogin(body)
    }
}