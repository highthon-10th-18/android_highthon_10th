package com.example.android_highthon_10th.presentation.main.profile

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel<ProfileViewModel.State>(savedStateHandle) {

    data class State(
        val name: String = ""
    ): BaseViewModel.State
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}