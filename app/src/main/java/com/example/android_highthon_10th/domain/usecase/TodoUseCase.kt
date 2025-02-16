package com.example.android_highthon_10th.domain.usecase

import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.TodoResponse
import com.example.android_highthon_10th.domain.repository.TodoRepository
import javax.inject.Inject

class TodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend fun getTodos(): List<TodoResponse> {
        return repository.getTodos()
    }

    suspend fun createTodo(body: TodoBody): TodoResponse {
        return repository.createTodo(body)
    }

    suspend fun editTodo(uuid: String, body: TodoEditBoby): TodoResponse {
        return repository.editTodo(uuid, body)
    }

    suspend fun deleteTodo(uuid: String) {
        repository.deleteTodo(uuid)
    }
}