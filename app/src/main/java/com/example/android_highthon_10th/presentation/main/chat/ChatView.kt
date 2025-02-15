package com.example.android_highthon_10th.presentation.main.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles

@Composable
fun ChatRoute() {
    ChatScreen()
}

@Composable
private fun ChatScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ) {

        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ChatScreen()
    }
}