package com.example.android_highthon_10th.domain.usecase

import com.auth0.jwt.JWT
import com.example.android_highthon_10th.domain.repository.SessionRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CheckTokenUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend fun isAccessTokenExpiring(): Boolean {
        val accessToken = sessionRepository.getAccessToken().first()
        return isTokenExpiringSoon(accessToken)
    }

    suspend fun isRefreshTokenExpiring(): Boolean {
        val refreshToken = sessionRepository.getRefreshToken().first()
        return isTokenExpiringSoon(refreshToken)
    }

    private fun isTokenExpiringSoon(jwtToken: String?, minutes: Long = 3): Boolean {
        return try {
            val decodedJWT = JWT.decode(jwtToken)

            val expTimeInMillis = decodedJWT.expiresAt?.time ?: return false
            val currentTimeInMillis = System.currentTimeMillis()
            val remainingTime = expTimeInMillis - currentTimeInMillis

            remainingTime <= minutes * 60 * 1000
        } catch (e: Exception) {
            true
        }
    }

}
