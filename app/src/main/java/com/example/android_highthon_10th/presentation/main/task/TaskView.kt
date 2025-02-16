package com.example.android_highthon_10th.presentation.main.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import com.example.android_highthon_10th.data.model.response.TodoResponse
import com.example.android_highthon_10th.presentation.component.CircleImage
import com.example.android_highthon_10th.presentation.component.Flex
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.IconButton
import com.example.android_highthon_10th.presentation.component.SegmentController
import com.example.android_highthon_10th.presentation.component.Switch
import com.example.android_highthon_10th.presentation.component.Width
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.noRippleClickable

@Composable
fun TaskRoute(
    viewModel: TaskViewModel = hiltViewModel(),
    navigateEdit: (AlarmResponse?, TodoResponse?) -> Unit,
    navigatePlus: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    TaskScreen(
        state = state,
        navigateEdit = navigateEdit,
        navigatePlus = navigatePlus,
        changeSwitch = viewModel::changeSwitch
    )
}

@Composable
private fun TaskScreen(
    state: TaskViewModel.State = TaskViewModel.State(),
    navigateEdit: (AlarmResponse?, TodoResponse?) -> Unit = { _, _ -> },
    navigatePlus: () -> Unit = {},
    changeSwitch: (TodoResponse, Boolean) -> Unit = { _, _ -> }
) {
    val scrollState = rememberScrollState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("알람", "할 일")

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Height(8)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "작업",
                    color = ColorStyles.CntDefault.primary,
                    style = TextStyles.heading.strong
                )
                IconButton(
                    res = R.drawable.ic_plus,
                    onClick = navigatePlus
                )
            }
            Height(8)
            SegmentController(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                selectedIndex = selectedTabIndex,
                textList = tabTitles,
                onClick = { selectedTabIndex = it }
            )
            Height(16)

            when (selectedTabIndex) {
                0 -> {
                    state.alarmList.forEach {
                        AlarmItem(it, navigateEdit)
                        Height(8)
                    }
                }
                1 -> {
                    state.todoList.forEach {
                        TodoItem(it, navigateEdit) { bool ->
                            changeSwitch(it, bool)
                        }
                        Height(8)
                    }
                }
            }
        }
    }
}

@Composable
private fun TodoItem(
    item: TodoResponse,
    navigateEdit: (AlarmResponse?, TodoResponse?) -> Unit,
    changeSwitch: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, ColorStyles.BgBase.border), RoundedCornerShape(16.dp))
            .background(ColorStyles.BgBase.elevated)
            .padding(16.dp)
            .noRippleClickable { navigateEdit(null, item) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.targetDate,
                style = TextStyles.headLine.strong,
                color = ColorStyles.CntDefault.primary
            )
            Height(12)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Switch(!item.isDone, changeSwitch)
            }
        }
    }
}

@Composable
private fun AlarmItem(
    item: AlarmResponse,
    navigateEdit: (AlarmResponse?, TodoResponse?) -> Unit,
) {
    val timeText = item.hour.toString().padStart(2, '0') + ":" + item.minute.toString().padStart(2, '0')
    val dayList = "${
        item.repeatDays.joinToString(", ") {
            when (it) {
                0 -> "일"
                1 -> "월"
                2 -> "화"
                3 -> "수"
                4 -> "목"
                5 -> "금"
                else -> "토"
            }
        }
    } 반복"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(BorderStroke(1.dp, ColorStyles.BgBase.border), RoundedCornerShape(16.dp))
            .background(ColorStyles.BgBase.elevated)
            .padding(16.dp)
            .noRippleClickable { navigateEdit(item, null) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
                CircleImage(
                    url = item.persona.profileImage
                )
                Width(4)
                Text(
                    text = item.persona.name,
                    style = TextStyles.label.default,
                    color = ColorStyles.CntDefault.primary
                )
            }
            Text(
                text = timeText,
                style = TextStyles.display.strong,
                color = ColorStyles.CntDefault.primary
            )
            Height(4)
            Text(
                text = if (dayList.length == 3) dayList + " 없음" else dayList,
                style = TextStyles.footnote.default,
                color = ColorStyles.CntDefault.quaternary
            )
        }
    }
}



@Preview
@Composable
private fun Preview() {
    AppTheme {
        TaskScreen()
    }
}