package com.example.android_highthon_10th.presentation.main.chat

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.presentation.component.CircleImage
import com.example.android_highthon_10th.presentation.component.Flex
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.IconButton
import com.example.android_highthon_10th.presentation.component.TextField
import com.example.android_highthon_10th.presentation.component.Width
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

@Composable
fun ChatRoomRoute(
    viewModel: ChatRoomViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    ChatRoomScreen(
        state = state,
        popBackStack = popBackStack,
        sendMessage = viewModel::onSendMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatRoomScreen(
    state: ChatRoomViewModel.State = ChatRoomViewModel.State(),
    popBackStack: () -> Unit = {},
    sendMessage: (String, Context) -> Unit = { _, _ -> }
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    res = R.drawable.ic_left_arrow,
                    iconAlignment = Alignment.CenterStart,
                    onClick = popBackStack
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "작업",
                    style = TextStyles.heading.strong,
                    color = ColorStyles.CntDefault.primary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
                    CircleImage(state.profile, 40.dp)
                    Width(8)

                    Column {
                        state.chatList.forEachIndexed { index, pair ->

                            if (index == 0) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(ColorStyles.BgBase.elevated, RoundedCornerShape(20.dp))
                                            .border(BorderStroke(1.dp, ColorStyles.BgBase.border), RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                                            text = state.chatList.first().first,
                                            style = TextStyles.body.default,
                                            color = ColorStyles.CntDefault.primary
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(ColorStyles.BgBase.elevated, RoundedCornerShape(20.dp))
                                    .border(BorderStroke(1.dp, ColorStyles.BgBase.border), RoundedCornerShape(20.dp))
                                    .padding(horizontal = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                                    text = pair.first,
                                    style = TextStyles.body.default,
                                    color = ColorStyles.CntDefault.primary
                                )
                            }
                        }
                    }
                }
                Flex()
                Row(
                    modifier = Modifier.fillMaxWidth().background(
                        color = ColorStyles.BgBase.elevated,
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var message by remember { mutableStateOf("") }
                    var isFocused by remember { mutableStateOf(false) } // 포커스 상태 저장
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f).padding(horizontal = 10.dp)) {
                        if (!isFocused) {
                            Text(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                                text = "부적절한 메시지는 검열될 수 있습니다...",
                                style = TextStyles.body.default,
                                color = ColorStyles.CntStatus.unselected
                            )
                        }
                        androidx.compose.material3.TextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier.background(Color.Transparent)
                                .onFocusChanged { focusState: FocusState ->
                                    isFocused = focusState.isFocused
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }
                    VerticalDivider(Modifier.fillMaxHeight(), 1.dp, ColorStyles.BgBase.border)
                    IconButton(
                        res = R.drawable.ic_send
                    ) {
                        sendMessage(context, message)
                        message = ""
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
        ChatRoomScreen()
    }
}