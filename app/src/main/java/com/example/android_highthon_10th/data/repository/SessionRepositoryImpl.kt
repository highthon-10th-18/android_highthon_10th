package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.SessionLocalDataSource
import com.example.android_highthon_10th.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val local: SessionLocalDataSource
): SessionRepository {
    override suspend fun setAccessToken(token: String) {
        local.setAccessToken(token)
    }

    override suspend fun setRefreshToken(token: String) {
        local.setRefreshToken(token)
    }

    override fun getAccessToken(): Flow<String?> {
        return local.getAccessToken()
    }

    override fun getRefreshToken(): Flow<String?> {
        return local.getRefreshToken()
    }

    override suspend fun removeTokens() {
        local.removeTokens()
    }

}