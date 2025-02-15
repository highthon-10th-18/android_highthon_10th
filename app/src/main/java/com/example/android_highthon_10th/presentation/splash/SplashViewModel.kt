package com.example.android_highthon_10th.presentation.splash

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.domain.usecase.CheckTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkTokenUseCase: CheckTokenUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel<SplashViewModel.State>(savedStateHandle) {

    init {
        init()
    }

    private fun init() = launch {
        val isRefreshTokenExpiring = checkTokenUseCase.isRefreshTokenExpiring()
        updateState {
            val nextPage = if (isRefreshTokenExpiring) {
                State.NextPage.Onboard
            } else {
                State.NextPage.Main
            }

            copy(nextPage = nextPage)
        }
    }

    data class State(
        val nextPage: NextPage? = null
    ): BaseViewModel.State {
        enum class NextPage {
            Main, Onboard
        }
    }
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}