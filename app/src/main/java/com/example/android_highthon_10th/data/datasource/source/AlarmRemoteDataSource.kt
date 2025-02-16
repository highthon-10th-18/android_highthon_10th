package com.example.android_highthon_10th.data.datasource.source

import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.response.AlarmResponse

interface AlarmRemoteDataSource {
    suspend fun getAlarms(): List<AlarmResponse>
    suspend fun createAlarm(body: AlarmBody): AlarmResponse
    suspend fun editAlarm(uuid: String, body: AlarmEditBody): AlarmResponse
    suspend fun deleteAlarm(uuid: String)
}