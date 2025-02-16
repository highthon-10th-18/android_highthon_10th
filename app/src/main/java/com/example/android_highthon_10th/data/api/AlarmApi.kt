package com.example.android_highthon_10th.data.api

import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AlarmApi {

    @GET("alarms")
    suspend fun getAlarms(): List<AlarmResponse>

    @POST("alarms")
    suspend fun createAlarm(@Body body: AlarmBody): AlarmResponse

    @PUT("alarms/{alarmUUID}")
    suspend fun editAlarm(@Path(value = "alarmUUID") alarmUUID: String, @Body body: AlarmEditBody): AlarmResponse

    @DELETE("alarms/{alarmUUID}")
    suspend fun deleteAlarm(@Path(value = "alarmUUID") alarmUUID: String)

}