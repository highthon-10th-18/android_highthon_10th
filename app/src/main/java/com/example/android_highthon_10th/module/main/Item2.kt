package com.example.android_highthon_10th.module.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

@Composable
fun Item2Route() {
    Item2Screen()
}

@Composable
private fun Item2Screen() {
    Scaffold(
        containerColor = ColorStyles.BgBase.default
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Item2",
                style = TextStyles.title.strong,
                color = ColorStyles.CntDefault.primary
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Item2Screen()
    }
}