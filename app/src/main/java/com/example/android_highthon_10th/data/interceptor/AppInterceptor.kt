package com.example.android_highthon_10th.data.interceptor

import android.util.Log
import com.example.android_highthon_10th.data.api.AuthApi
import com.example.android_highthon_10th.data.model.request.RefreshTokenBody
import com.example.android_highthon_10th.data.model.response.JwtTokenResponse
import com.example.android_highthon_10th.domain.repository.SessionRepository
import com.example.android_highthon_10th.util.CommonException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val authApi: AuthApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            var response = doRequest(chain)

            if (response.code == 401) {
                response.close()
                runBlocking {
                    refreshToken()
                }
                response = doRequest(chain)
            }

            if (response.code != 200) throw CommonException.UnknownError()

            return response
        } catch (e: Exception) {
            val error = if (e is CommonException) e else CommonException.UnknownError()
            Log.e("에러 확인,AppInterceptor", e.message ?: error.message ?: "에러 확인 불가")
            throw error
        }
    }

    private fun doRequest(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val accessToken = runBlocking { sessionRepository.getAccessToken().first() }

        return chain.proceed(
            request
                .newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
        )
    }

    private suspend fun refreshToken() {
        val refreshToken = sessionRepository.getRefreshToken().firstOrNull()

        try {
            if (refreshToken.isNullOrBlank()) {
                sessionRepository.removeTokens()
                Log.e("에러 확인, AppInterceptor - refreshToken", "리프래쉬 토큰 값 없음")

                throw CommonException.UnauthorizedError()
            }

            val response = authApi.refresh(RefreshTokenBody(refreshToken))

            when {
                tokenCheck(response) -> {
                    sessionRepository.setAccessToken(response.accessToken)
                    sessionRepository.setRefreshToken(response.refreshToken)
                }
                else -> {
                    sessionRepository.removeTokens()

                    Log.e("에러 확인, AppInterceptor - refreshToken", "tokenCheck 실패")
                    throw CommonException.UnauthorizedError()
                }
            }
        } catch (e: Exception) {
            sessionRepository.removeTokens()

            val error = CommonException.UnknownError()
            Log.e("에러 확인, AppInterceptor - refreshToken", e.message ?: error.message)

            throw CommonException.UnauthorizedError()
        }
    }

    private fun tokenCheck(authResponse: JwtTokenResponse?): Boolean {
        val accessTokenCheck = !authResponse?.accessToken.isNullOrBlank()
        val refreshTokenCheck = !authResponse?.refreshToken.isNullOrBlank()

        return accessTokenCheck && refreshTokenCheck
    }

}