package com.example.android_highthon_10th.data.interceptor

import android.util.Log
import com.example.android_highthon_10th.util.CommonException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code != 200) {
                val error = CommonException.UnknownError()
                Log.e("에러 확인, DefaultInterceptor - intercept", "200 아님")

                throw error
            }

            return response
        } catch (e: Exception) {
            val error = if (e is CommonException) e else CommonException.UnknownError()
            Log.e("에러 확인, DefaultInterceptor - catch", e.message ?: error.message ?: "에러 확인 불가")
            throw error
        }
    }

}