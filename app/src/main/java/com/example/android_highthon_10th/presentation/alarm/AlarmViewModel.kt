package com.example.android_highthon_10th.presentation.alarm

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.example.android_highthon_10th.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): BaseViewModel<AlarmViewModel.State>(savedStateHandle) {

    init {
        init()
    }

    private fun init() = launchWithLoading {
        val time = "${getCurrentTimeFormatted()}입니다."
        updateState { copy(time = time) }
    }

    fun init2() = launchWithLoading {
        updateState {
            val chatList = chatList.toMutableList()
            chatList.add("${time}이 되었어요. 조금씩 눈을 떠 보세요… 아침 햇살이 따뜻하게 맞아주고 있답니다.")
            copy(chatList = chatList)
        }
        delay(300)
        updateState {
            val chatList = chatList.toMutableList()
            chatList.add("오늘도 힘든 일이 없도록 제가 옆에서 지켜볼 테니, 천천히 준비하시면 돼요. ✨")
            copy(chatList = chatList)
        }

    }

    private fun getCurrentTimeFormatted(): String {
        val dateFormat = SimpleDateFormat("HH시 mm분", Locale.getDefault())
        return dateFormat.format(Date())
    }

    data class State(
        val time: String = "",
        val imageUrl: String = "https://cdn.discordapp.com/attachments/1339576540912156674/1340512543755599892/ECB59CEC95A0EC9D98_EC9584EC9DB4_2EAB8B0_5ED9994_1.png?ex=67b2a117&is=67b14f97&hm=fafc778c210736d9ce015daa479f37b88efb7466cfa25672e59abb118d8b355f&",
        val chatList: List<String> = listOf()
    ): BaseViewModel.State

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}