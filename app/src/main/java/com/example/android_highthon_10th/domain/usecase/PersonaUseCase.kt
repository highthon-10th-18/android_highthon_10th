package com.example.android_highthon_10th.domain.usecase

import com.example.android_highthon_10th.data.model.response.PersonasDetailResponse
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import com.example.android_highthon_10th.domain.repository.PersonaRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class PersonaUseCase @Inject constructor(
    private val repository: PersonaRepository
) {
    suspend fun getPersonas(): List<PersonasResponse> {
        return repository.getPersonas()
    }

    suspend fun postPersona(
        name: String,
        description: String,
        profileImage: MultipartBody.Part
    ): PersonasDetailResponse {
        return repository.postPersona(name, description, profileImage)
    }

    suspend fun getPersonaDetail(uuid: Int): PersonasDetailResponse {
        return repository.getPersonaDetail(uuid)
    }

}