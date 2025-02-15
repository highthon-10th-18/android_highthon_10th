package com.example.android_highthon_10th.domain.usecase

import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.domain.repository.SessionRepository
import com.example.android_highthon_10th.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(body: SignUpBody) {
        val response = signUpRepository.signUpRequest(body)

        sessionRepository.setAccessToken(response.accessToken)
        sessionRepository.setRefreshToken(response.refreshToken)
    }
}
