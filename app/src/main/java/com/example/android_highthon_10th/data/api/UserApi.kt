package com.example.android_highthon_10th.data.api

import com.example.android_highthon_10th.data.model.response.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("auth/me")
    fun getUser(): UserResponse
}