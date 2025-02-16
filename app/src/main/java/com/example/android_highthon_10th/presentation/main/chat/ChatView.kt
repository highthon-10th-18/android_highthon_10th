package com.example.android_highthon_10th.presentation.main.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.presentation.component.CircleImage
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.Width
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.noRippleClickable

@Composable
fun ChatRoute(
    viewModel: ChatViewModel = hiltViewModel(),
    navigate: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    ChatScreen(
        navigate = navigate,
        state = state
    )
}

@Composable
private fun ChatScreen(
    navigate: () -> Unit,
    state: ChatViewModel.State = ChatViewModel.State()
) {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 9.dp),
                text = "채팅",
                style = TextStyles.heading.strong,
                color = ColorStyles.CntDefault.primary
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .noRippleClickable { navigate() }
            ) {
                state.chatRoomList.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleImage(
                            state.profile,
                            size = 48.dp
                        )
                        Width(12)
                        Column {
                            Text(it.name, style = TextStyles.body.strong, color = ColorStyles.CntDefault.primary)
                            Text(it.name, style = TextStyles.footnote.default, color = ColorStyles.CntDefault.tertiary)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ChatScreen({})
    }
}