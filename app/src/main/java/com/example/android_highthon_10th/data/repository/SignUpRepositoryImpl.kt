package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.AuthRemoteDataSource
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse
import com.example.android_highthon_10th.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
): SignUpRepository {

    override suspend fun signUpRequest(body: SignUpBody): JwtTokenResponse {
        return remote.postSignUp(body)
    }
}