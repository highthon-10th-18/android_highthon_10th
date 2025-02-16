package com.example.android_highthon_10th.presentation.main.chat

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import com.example.android_highthon_10th.data.model.request.AlarmBody
import com.example.android_highthon_10th.domain.usecase.AlarmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val alarmUseCase: AlarmUseCase
): BaseViewModel<ChatRoomViewModel.State>(savedStateHandle) {

    fun onSendMessage(value: String, context: Context) = launchWithLoading {
        updateState {
            val chatList = chatList.toMutableList()
            chatList.add(value to false)
            copy(chatList = chatList)
        }

        delay(1000)

        updateState {
            val chatList = chatList.toMutableList()
            chatList.add("요청하신 시간에 알람을 맞춰두었어요. 평소보다 피곤해 보이시던데, 알람 울리기 전까지 잠시라도 편히 쉬세요…" to true)
            copy(chatList = chatList)
        }

        val body = AlarmBody(12, 26, "435dd533-a18b-4832-b42d-0c5a572a34ff", listOf())
        alarmUseCase.createAlarm(context, body)

    }

    data class State(
        val name: String = "쿠로카와 아카네",
        val profile: String = "https://cdn.discordapp.com/attachments/1339576540912156674/1340512543755599892/ECB59CEC95A0EC9D98_EC9584EC9DB4_2EAB8B0_5ED9994_1.png?ex=67b2a117&is=67b14f97&hm=fafc778c210736d9ce015daa479f37b88efb7466cfa25672e59abb118d8b355f&",
        val chatList: List<Pair<String, Boolean>> = listOf()
    ): BaseViewModel.State
    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}