package com.example.android_highthon_10th.data.datasource

import com.example.android_highthon_10th.data.api.TodoApi
import com.example.android_highthon_10th.data.datasource.source.TodoRemoteDataSource
import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.TodoResponse
import javax.inject.Inject

class TodoRemoteDataSourceImpl @Inject constructor (
    private val api: TodoApi
) : TodoRemoteDataSource {
    override suspend fun getTodos(): List<TodoResponse> {
        return api.getTodos()
    }

    override suspend fun createTodo(body: TodoBody): TodoResponse {
        return api.createTodo(body)
    }

    override suspend fun editTodo(uuid: String, body: TodoEditBoby): TodoResponse {
        return api.editTodo(uuid, body)
    }

    override suspend fun deleteTodo(uuid: String) {
        api.deleteTodo(uuid)
    }

}