package com.example.android_highthon_10th.presentation.main.task.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.data.model.response.PersonasResponse
import com.example.android_highthon_10th.presentation.component.CircleImage
import com.example.android_highthon_10th.presentation.component.Flex
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.IconButton
import com.example.android_highthon_10th.presentation.component.SegmentController
import com.example.android_highthon_10th.presentation.component.TextField
import com.example.android_highthon_10th.presentation.component.Width
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.noRippleClickable
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskAddRoute(
    viewModel: TaskAddViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getPersonas()
        viewModel.successCreated.collectLatest {
            popBackStack()
        }
    }

    TaskDetailScreen(
        state = state,

        createAlarm = { viewModel.createAlarm(context) },
        createTodo = viewModel::createTodo,

        searchPersonas = viewModel::searchPersonas,
        onNameChanged = viewModel::onNameChanged,
        onDateChanged = viewModel::onDateChanged,
        onTimeChanged = viewModel::onTimeChanged,
        onPersonaChanged = viewModel::onPersonaChanged,
        onDateListChanged = viewModel::onDateListChanged,

        popBackStack = popBackStack
    )
}

@Composable
private fun TaskDetailScreen(
    state: TaskAddViewModel.State = TaskAddViewModel.State(),

    createAlarm: () -> Unit = {},
    createTodo: () -> Unit = {},

    searchPersonas: (String) -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    onDateChanged: (String) -> Unit = {},
    onTimeChanged: (String) -> Unit = {},
    onPersonaChanged: (PersonasResponse) -> Unit = {},
    onDateListChanged: (List<Int>) -> Unit = {},

    popBackStack: () -> Unit = {}
) {
    var isPersona by remember { mutableStateOf(false) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("알람", "할 일")

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ) {
            if (isPersona) {
                var personaSearch by remember { mutableStateOf("") }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        res = R.drawable.ic_left_arrow,
                        iconAlignment = Alignment.CenterStart,
                        onClick = { 
                            isPersona = false
                        }
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "최애 선택",
                        style = TextStyles.heading.strong,
                        color = ColorStyles.CntDefault.primary
                    )
                }
                Height(16)
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    labelText = "검색",
                    placeholderText = "알베르트 아인슈타인",
                    value = personaSearch,
                    onValueChanged = {
                        personaSearch = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(

                        onSearch = {
                            searchPersonas(personaSearch)
                        }
                    )
                )
                Height(24)
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = ColorStyles.BgBase.border
                )
                Height(24)
                state.filterPersonaList.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .noRippleClickable {
                                onPersonaChanged(it)
                                isPersona = false
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleImage(it.profileImage, size = 64.dp)
                        Width(12)
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .noRippleClickable { onPersonaChanged(it) }) {
                            Text(
                                text = it.name,
                                color = ColorStyles.CntDefault.primary,
                                style = TextStyles.body.strong
                            )
                            Text(
                                text = it.description,
                                color = ColorStyles.CntDefault.quaternary,
                                style = TextStyles.footnote.default,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Height(16)
                }
            } else {
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
                Height(16)
                SegmentController(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    selectedIndex = selectedTabIndex,
                    textList = tabTitles,
                    onClick = { selectedTabIndex = it }
                )
                if (selectedTabIndex == 0) {
                    Height(16)
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        labelText = "시간",
                        placeholderText = "00시 00분",
                        value = state.time ?: "",
                        onValueChanged = onTimeChanged
                    )
                    Height(8)
                    Box(
                        modifier = Modifier.noRippleClickable { isPersona = true }
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .noRippleClickable { isPersona = true },
                            labelText = "최애 선택",
                            placeholderText = "최애를 입력해주세요",
                            value = state.persona?.name ?: "",
                            onValueChanged = { isPersona = true }
                        )
                    }

                    Height(24)
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = "반복 할 요일",
                        style = TextStyles.heading.strong,
                        color = ColorStyles.CntDefault.primary
                    )
                    Height(8)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        val dateList = listOf("월", "화", "수", "목", "금", "토", "일")
                        dateList.forEachIndexed { index, date ->
                            val (background, textColor, border) = if (
                                state.dateList.map {
                                    when (it) {
                                        0 -> "일"
                                        1 -> "월"
                                        2 -> "화"
                                        3 -> "수"
                                        4 -> "목"
                                        5 -> "금"
                                        else -> "토"
                                    }
                                }.contains(date)
                            ) {
                                Triple(ColorStyles.CntDefault.primary, ColorStyles.BgBase.default, ColorStyles.BgBase.default)
                            } else {
                                Triple(ColorStyles.BgBase.elevated, ColorStyles.CntDefault.primary, ColorStyles.BgBase.border)
                            }

                            Box(
                                modifier = Modifier
                                    .size(45.dp, 36.dp)
                                    .clip(CircleShape)
                                    .background(background, CircleShape)
                                    .border(BorderStroke(1.dp, border), CircleShape)
                                    .clickable {
                                        val dateIndex = if (date == "일") 0 else index + 1
                                        val newDateList = state.dateList
                                            .toMutableList()
                                            .apply {
                                                if (this.contains(dateIndex)) {
                                                    remove(dateIndex)
                                                } else {
                                                    add(dateIndex)
                                                }
                                                sort()
                                            }

                                        onDateListChanged(newDateList)
                                    }
                                ,
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = date,
                                    textAlign = TextAlign.Center,
                                    style = TextStyles.body.strong,
                                    color = textColor
                                )
                            }
                        }
                    }
                    Height(16)
                } else {
                    Height(16)
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        labelText = "할 일 이름",
                        placeholderText = "설거지 하기",
                        value = state.name ?: "",
                        onValueChanged = onNameChanged
                    )
                    Height(8)
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        labelText = "날짜 (선택)",
                        placeholderText = "2025. 02. 15",
                        value = state.date ?: "",
                        onValueChanged = onDateChanged
                    )
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
                    onClick = {
                        if (selectedTabIndex == 0) createAlarm() else createTodo()
                    }
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
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        TaskDetailScreen()
    }
}