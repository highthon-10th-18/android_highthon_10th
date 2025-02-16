package com.example.android_highthon_10th.domain.repository

import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.TodoResponse

interface TodoRepository {
    suspend fun getTodos(): List<TodoResponse>
    suspend fun createTodo(body: TodoBody): TodoResponse
    suspend fun editTodo(uuid: String, body: TodoEditBoby): TodoResponse
    suspend fun deleteTodo(uuid: String)
}