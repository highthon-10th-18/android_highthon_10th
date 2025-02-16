package com.example.android_highthon_10th.data.repository

import com.example.android_highthon_10th.data.datasource.source.TodoRemoteDataSource
import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.TodoResponse
import com.example.android_highthon_10th.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val remote: TodoRemoteDataSource
): TodoRepository {
    override suspend fun getTodos(): List<TodoResponse> {
        return remote.getTodos()
    }

    override suspend fun createTodo(body: TodoBody): TodoResponse {
        return remote.createTodo(body)
    }

    override suspend fun editTodo(uuid: String, body: TodoEditBoby): TodoResponse {
        return remote.editTodo(uuid, body)
    }

    override suspend fun deleteTodo(uuid: String) {
        remote.deleteTodo(uuid)
    }
}