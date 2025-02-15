package com.example.android_highthon_10th.presentation.main

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.android_highthon_10th.presentation.component.BottomNavigationBar
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles

@Composable
fun MainRoute(
    bottomNavController: NavHostController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    MainScreen(
        bottomNavController = bottomNavController,
        startDestination = startDestination,
        builder = builder
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainScreen(
    bottomNavController: NavHostController = rememberNavController(),
    startDestination: String = "",
    builder: NavGraphBuilder.() -> Unit = { }
) {
    Scaffold(
        containerColor = ColorStyles.BgBase.elevated,
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) {
        NavHost(
            navController = bottomNavController,
            startDestination = startDestination,
            builder = builder,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        MainScreen()
    }
}