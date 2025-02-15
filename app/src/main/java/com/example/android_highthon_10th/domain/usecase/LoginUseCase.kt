package com.example.android_highthon_10th.domain.usecase

import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.domain.repository.LoginRepository
import com.example.android_highthon_10th.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(body: LoginBody) {
        val response = loginRepository.loginRequest(body)

        sessionRepository.setAccessToken(response.accessToken)
        sessionRepository.setRefreshToken(response.refreshToken)
    }
}
