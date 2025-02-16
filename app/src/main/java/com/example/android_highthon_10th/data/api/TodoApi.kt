package com.example.android_highthon_10th.data.api

import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.TodoResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>

    @POST("todos")
    suspend fun createTodo(@Body body: TodoBody): TodoResponse

    @PUT("todos/{TodoUUID}")
    suspend fun editTodo(@Path(value = "TodoUUID") todoUUID: String, @Body body: TodoEditBoby): TodoResponse

    @DELETE("todos/{TodoUUID}")
    suspend fun deleteTodo(@Path(value = "TodoUUID") todoUUID: String)

}