package com.example.android_highthon_10th.presentation.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.util.findActivity
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateOnboard: () -> Unit,
    navigateMain: () -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity()

    val state = viewModel.state.collectAsState()

    if (state.value.nextPage == SplashViewModel.State.NextPage.Main) {
        navigateMain()
    } else {
        navigateOnboard()
    }

    BackHandler { activity.finish() }

    SplashScreen()
}

@Composable
private fun SplashScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(ColorStyles.BgBase.default),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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