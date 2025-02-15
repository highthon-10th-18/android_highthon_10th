package com.example.android_highthon_10th.presentation.onboard

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.SignUpBody
import com.example.android_highthon_10th.domain.usecase.SignUpUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateEmailUseCase
import com.example.android_highthon_10th.domain.usecase.ValidateNameUseCase
import com.example.android_highthon_10th.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val signUpUseCase: SignUpUseCase
): BaseViewModel<SignUpViewModel.State>(savedStateHandle) {

    private val _successSignUp: MutableSharedFlow<Unit> = MutableSharedFlow()
    val successSignUp: SharedFlow<Unit> = _successSignUp.asSharedFlow()

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

    fun onNameChanged(value: String) {
        val isValidated = validateNameUseCase(value)

        updateState {
            copy(name = if (isValidated) value else name)
        }
    }

    fun signUp() = launchWithLoading {
        val body = SignUpBody(
            email = state.value.email,
            name = state.value.name,
            password = state.value.password
        )
        signUpUseCase(body)
    }


    data class State(
        val email: String = "",
        val password: String = "",
        val name: String = ""
    ): BaseViewModel.State
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}