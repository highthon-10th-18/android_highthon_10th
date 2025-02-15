package com.example.android_highthon_10th.data.datasource.source

import com.example.android_highthon_10th.data.model.response.PersonasDetailResponse
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import okhttp3.MultipartBody

interface PersonaRemoteDataSource {
    suspend fun getPersonas(): List<PersonasResponse>
    suspend fun postPersona(name: String, description: String, profileImage: MultipartBody.Part): PersonasDetailResponse
    suspend fun getPersonaDetail(uuid: Int): PersonasDetailResponse
}