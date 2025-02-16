package com.example.android_highthon_10th.presentation.main.task.detail

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.data.model.request.AlarmEditBody
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.data.model.request.TodoBody
import com.example.android_highthon_10th.data.model.request.TodoEditBoby
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import com.example.android_highthon_10th.domain.usecase.AlarmUseCase
import com.example.android_highthon_10th.domain.usecase.LoginUseCase
import com.example.android_highthon_10th.domain.usecase.PersonaUseCase
import com.example.android_highthon_10th.domain.usecase.TodoUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import com.example.android_highthon_10th.util.formatDate
import com.example.android_highthon_10th.util.parseTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val alarmUseCase: AlarmUseCase,
    private val todoUseCase: TodoUseCase,
    private val personaUseCase: PersonaUseCase
): BaseViewModel<TaskDetailViewModel.State>(savedStateHandle) {

    private val _success: MutableSharedFlow<Unit> = MutableSharedFlow()
    val success: SharedFlow<Unit> = _success.asSharedFlow()

    init {
        getPersonas()
    }

    fun deleteAlarm(context: Context, uuid: String) = launchWithLoading {
        alarmUseCase.deleteAlarm(context, uuid)
        _success.emit(Unit)
    }

    fun deleteTodo(uuid: String) = launchWithLoading {
        todoUseCase.deleteTodo(uuid)
        _success.emit(Unit)
    }

    fun editAlarm(context: Context, uuid: String) = launchWithLoading {
        with(currentState) {
            if (time == null) return@with
            if (persona == null) return@with

            val (hour, minute) = parseTime(time)

            val body = AlarmEditBody(
                hour = hour,
                minute = minute,
                personaUUID = persona.uuid,
                repeatDays = dateList,
                isActivated = true
            )

            alarmUseCase.editAlarm(context, uuid, body)
            _success.emit(Unit)
        }
    }

    fun editTodo(uuid: String) = launchWithLoading {
        with(currentState) {
            if (name == null) return@with
            if (persona == null) return@with
            if (date == null) return@with

            val body = TodoEditBoby(
                name = name,
                personaUUID = persona.uuid,
                targetDate = formatDate(date),
                isDone = false
            )
            todoUseCase.editTodo(uuid, body)
            _success.emit(Unit)
        }
    }

    fun getPersonas() = launchWithLoading {
        val personaList = personaUseCase.getPersonas()

        updateState { copy(personaList = personaList, filterPersonaList = personaList ) }
    }

    fun createAlarm(context: Context) = launchWithLoading {
        with(currentState) {
            if (time == null) return@with
            if (persona == null) return@with

            val (hour, minute) = parseTime(time)

            val body = AlarmBody(
                hour = hour,
                minute = minute,
                personaUUID = persona.uuid,
                repeatDays = dateList
            )
            alarmUseCase.createAlarm(context, body)
            _success.emit(Unit)
        }
    }

    fun createTodo() = launchWithLoading {
        with(currentState) {
            if (name == null) return@with
            if (persona == null) return@with
            if (date == null) return@with

            val body = TodoBody(
                name = name,
                personaUUID = persona.uuid,
                targetDate = formatDate(date)
            )
            todoUseCase.createTodo(body)
            _success.emit(Unit)
        }
    }

    fun onNameChanged(value: String) = updateState {
        copy(name = value)
    }

    fun searchPersonas(value: String) = launch {
        if (value.isBlank()) return@launch

        val filterPersonaList = state.value.personaList.filter { it.name.contains(value) }
        updateState { copy(filterPersonaList = filterPersonaList) }
    }

    fun onDateChanged(value: String) = updateState {
        copy(date = value)
    }

    fun onTimeChanged(value: String) = updateState {
        copy(date = value)
    }

    fun onPersonaChanged(value: PersonasResponse) = updateState {
        copy(persona = value)
    }

    fun onDateListChanged(value: List<Int>) = updateState {
        copy(dateList = value)
    }

    data class State(
        val personaList: List<PersonasResponse> = listOf(),
        val filterPersonaList: List<PersonasResponse> = listOf(),

        val name: String? = null,
        val date: String? = null,

        val time: String? = null,
        val persona: PersonasResponse? = null,
        val dateList: List<Int> = listOf()
    ): BaseViewModel.State {
        enum class TaskType {
            Alarm, Todo
        }
    }

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}