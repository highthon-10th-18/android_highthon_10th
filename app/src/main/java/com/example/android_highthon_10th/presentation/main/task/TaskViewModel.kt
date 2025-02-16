package com.example.android_highthon_10th.presentation.main.task

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import com.example.android_highthon_10th.data.model.response.TodoResponse
import com.example.android_highthon_10th.domain.usecase.AlarmUseCase
import com.example.android_highthon_10th.domain.usecase.LoginUseCase
import com.example.android_highthon_10th.domain.usecase.TodoUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val alarmUseCase: AlarmUseCase,
    private val todoUseCase: TodoUseCase
): BaseViewModel<TaskViewModel.State>(savedStateHandle) {

    fun init() = launchWithLoading {
        val alarmList = alarmUseCase.getAlarms()
        val todoList = todoUseCase.getTodos()
        updateState { copy(alarmList = alarmList, todoList = todoList) }
    }

    fun changeSwitch(item:TodoResponse, boolean: Boolean) = launchWithLoading {
        todoUseCase.editTodo(item.uuid, TodoEditBoby(item.name, isDone = boolean, item.uuid, targetDate = item.targetDate))
    }

    init {
        init()
    }

    data class State(
        val alarmList: List<AlarmResponse> = listOf(),
        val todoList: List<TodoResponse> = listOf()
    ): BaseViewModel.State

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}