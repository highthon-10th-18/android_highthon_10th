package com.example.android_highthon_10th.presentation.onboard

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.LoginBody
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.domain.usecase.LoginUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import com.example.android_highthon_10th.util.CommonException
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
        updateState {
            copy(email = value)
        }
    }

    fun onPasswordChanged(value: String) {

        updateState {
            copy(password = value)
        }
    }

    fun login() = launchWithLoading {
//        val emailValidateCheck = validateEmailUseCase(state.value.email)
//        val passwordValidateCheck = validatePasswordUseCase(state.value.password)

//        if (!emailValidateCheck || !passwordValidateCheck) throw CommonException.UnknownError()

        val body = LoginBody(
            email = state.value.email,
            password = state.value.password
        )
        loginUseCase(body)
        _successLogin.emit(Unit)
    }

    data class State(
        val email: String = "",
        val password: String = "",
    ): BaseViewModel.State

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}