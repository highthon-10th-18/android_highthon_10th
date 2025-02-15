package com.example.android_highthon_10th.module.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.component.Icon
import com.example.android_highthon_10th.component.IconSizeType
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip
import com.example.android_highthon_10th.util.findActivity
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    navigateMain: () -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity()

    BackHandler { activity.finish() }

    LaunchedEffect(Unit) {
        delay(500L)
        navigateMain()
    }

    SplashScreen()
}

@Composable
private fun SplashScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ColorChip.white),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                IconSizeType.Size64,
                R.drawable.ic_default_size_icon,
                ColorChip.black
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SplashScreen()
    }
}