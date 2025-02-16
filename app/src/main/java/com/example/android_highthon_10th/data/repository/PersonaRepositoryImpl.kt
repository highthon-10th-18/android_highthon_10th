package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.PersonaRemoteDataSource
import com.example.android_highthon_10th.data.model.response.PersonasDetailResponse
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import com.example.android_highthon_10th.domain.repository.PersonaRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonaRepositoryImpl @Inject constructor(
    private val remote: PersonaRemoteDataSource
): PersonaRepository {
    override suspend fun getPersonas(): List<PersonasResponse> {
        return remote.getPersonas()
    }

    override suspend fun postPersona(
        name: String,
        description: String,
        profileImage: MultipartBody.Part
    ): PersonasDetailResponse {
        return remote.postPersona(name, description, profileImage)
    }

    override suspend fun getPersonaDetail(uuid: Int): PersonasDetailResponse {
        return remote.getPersonaDetail(uuid)
    }

}