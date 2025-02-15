package com.example.android_highthon_10th.domain.usecase

import com.example.android_highthon_10th.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    fun getAccessToken(): Flow<String?> {
        return sessionRepository.getAccessToken()
    }

    fun getRefreshToken(): Flow<String?> {
        return sessionRepository.getRefreshToken()
    }

    suspend fun removeTokens() {
        sessionRepository.removeTokens()
    }
}