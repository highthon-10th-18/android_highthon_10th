package com.example.android_highthon_10th.presentation.alarm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.presentation.component.CircleImage
import com.example.android_highthon_10th.presentation.component.Flex
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.Width
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.findActivity
import kotlinx.coroutines.delay

@Composable
fun AlarmRoute(
    viewModel: AlarmViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context.findActivity()

    val state by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        delay(1000)
        viewModel.init2()
    }
    AlarmScreen(
        state = state,
        popBackStack = {
            activity.findActivity()
            activity.finish()
        }
    )
}

@Composable
private fun AlarmScreen(
    state: AlarmViewModel.State = AlarmViewModel.State(),
    popBackStack: () -> Unit = {}
) {


    Scaffold { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(horizontal = 16.dp)
                .padding(paddingValue)
        ) {
            Height(60)
            Text(
                text = "현재 시각",
                style = TextStyles.heading.default,
                color = ColorStyles.CntDefault.quaternary
            )
            Text(
                text = state.time,
                style = TextStyles.display.strong,
                color = ColorStyles.CntDefault.primary
            )
            Height(8)

            Row {
                CircleImage(state.imageUrl, 40.dp)
                Width(8)
                Column {
                    state.chatList.forEach {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(ColorStyles.BgBase.elevated, RoundedCornerShape(20.dp))
                                .border(BorderStroke(1.dp, ColorStyles.BgBase.border), RoundedCornerShape(20.dp))
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                                text = it,
                                style = TextStyles.body.default,
                                color = ColorStyles.CntDefault.primary
                            )
                        }
                        Height(4)
                    }
                }
            }
            Flex()

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = ColorStyles.CntDefault.primary),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, ColorStyles.BgBase.border),
                onClick = popBackStack
            ) {
                Text(
                    text = "완료",
                    color = ColorStyles.BgBase.default,
                    style = TextStyles.title.strong
                )
            }
        }
    }
}

