package com.example.android_highthon_10th.data.api

import com.example.android_highthon_10th.data.model.response.PersonasDetailResponse
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PersonaApi {

    @GET("personas")
    fun getPersonas(): List<PersonasResponse>

    @Multipart
    @POST("personas")
    suspend fun postPersona(
        @Part("name") name: String,
        @Part("description") description: String,
        @Part profileImage: MultipartBody.Part
    ): PersonasDetailResponse

    @GET("personas/{uuid}")
    fun getPersonaDetail(@Path(value = "uuid") uuid : Int): PersonasDetailResponse

}