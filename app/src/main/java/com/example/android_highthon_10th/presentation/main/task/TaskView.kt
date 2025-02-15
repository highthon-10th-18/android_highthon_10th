package com.example.android_highthon_10th.presentation.main.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.SegmentController
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

@Composable
fun TaskRoute() {
    TaskScreen()
}

@Composable
private fun TaskScreen() {
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
            Height(92)
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = "작업",
                color = ColorStyles.CntDefault.primary,
                style = TextStyles.display.strong
            )
            Height(16)
            SegmentController(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                selectedIndex = selectedTabIndex,
                textList = tabTitles,
                onClick = { selectedTabIndex = it }
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