package com.example.android_highthon_10th.presentation.onboard

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.domain.usecase.LoginUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginViewModel.State>(savedStateHandle) {

    private val _successLogin: MutableSharedFlow<Unit> = MutableSharedFlow()
    val successLogin: SharedFlow<Unit> = _successLogin.asSharedFlow()

    fun onEmailChanged(value: String) {
        val isValidated = validateEmailUseCase(value)

        updateState {
            copy(email = if (isValidated) value else email)
        }
    }

    fun onPasswordChanged(value: String) {
        val isValidated = validatePasswordUseCase(value)

        updateState {
            copy(password = if (isValidated) value else password)
        }
    }

    fun login() = launchWithLoading {
        val body = LoginBody(
            email = state.value.email,
            password = state.value.password
        )
        loginUseCase(body)
    }

    data class State(
        val email: String = "",
        val password: String = "",
    ): BaseViewModel.State

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}