package com.example.android_highthon_10th.data.datasource.source

import kotlinx.coroutines.flow.Flow

interface SessionLocalDataSource {
    suspend fun setAccessToken(token: String)
    suspend fun setRefreshToken(token: String)

    fun getAccessToken(): Flow<String?>
    fun getRefreshToken(): Flow<String?>

    suspend fun removeTokens()
}