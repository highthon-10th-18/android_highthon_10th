package com.example.android_highthon_10th.presentation.main.chat

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): BaseViewModel<ChatViewModel.State>(savedStateHandle) {

    data class State(
        val chatRoomList: List<ChatRoom> = listOf()
    ): BaseViewModel.State {
        data class ChatRoom (
            val name: String = "",
            val chatList: List<String> = listOf()
        )
    }
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}