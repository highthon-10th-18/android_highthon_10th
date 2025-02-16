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
        val profile: String = "https://cdn.discordapp.com/attachments/1339576540912156674/1340512543755599892/ECB59CEC95A0EC9D98_EC9584EC9DB4_2EAB8B0_5ED9994_1.png?ex=67b2a117&is=67b14f97&hm=fafc778c210736d9ce015daa479f37b88efb7466cfa25672e59abb118d8b355f&",
        val chatRoomList: List<ChatRoom> = listOf(ChatRoom())
    ): BaseViewModel.State {
        data class ChatRoom (
            val name: String = "쿠로카와 아카네",
            val chatList: List<String> = listOf("...")
        )
    }
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}