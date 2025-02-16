package com.example.android_highthon_10th.domain.repository

import com.example.android_highthon_10th.data.model.response.PersonasDetailResponse
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import okhttp3.MultipartBody

interface PersonaRepository {
    suspend fun getPersonas(): List<PersonasResponse>
    suspend fun postPersona(name: String, description: String, profileImage: MultipartBody.Part): PersonasDetailResponse
    suspend fun getPersonaDetail(uuid: Int): PersonasDetailResponse
}