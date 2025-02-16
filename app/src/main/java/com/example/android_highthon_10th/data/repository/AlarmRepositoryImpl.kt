package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.AlarmRemoteDataSource
import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import com.example.android_highthon_10th.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val remote: AlarmRemoteDataSource
): AlarmRepository {
    override suspend fun getAlarms(): List<AlarmResponse> {
        return remote.getAlarms()
    }

    override suspend fun createAlarm(body: AlarmBody): AlarmResponse {
        return remote.createAlarm(body)
    }

    override suspend fun editAlarm(uuid: String, body: AlarmEditBody): AlarmResponse {
        return remote.editAlarm(uuid, body)
    }

    override suspend fun deleteAlarm(uuid: String) {
        remote.deleteAlarm(uuid)
    }
}