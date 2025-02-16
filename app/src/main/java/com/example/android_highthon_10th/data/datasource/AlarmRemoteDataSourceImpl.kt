package com.example.android_highthon_10th.data.datasource

import com.example.android_highthon_10th.data.api.AlarmApi
import com.example.android_highthon_10th.data.datasource.source.AlarmRemoteDataSource
import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import javax.inject.Inject

class AlarmRemoteDataSourceImpl @Inject constructor (
    private val api: AlarmApi
) : AlarmRemoteDataSource {
    override suspend fun getAlarms(): List<AlarmResponse> {
        return api.getAlarms()
    }

    override suspend fun createAlarm(body: AlarmBody): AlarmResponse {
        return api.createAlarm(body)
    }

    override suspend fun editAlarm(uuid: String, body: AlarmEditBody): AlarmResponse {
        return api.editAlarm(uuid, body)
    }

    override suspend fun deleteAlarm(uuid: String) {
        api.deleteAlarm(uuid)
    }

}