package com.example.android_highthon_10th

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.android_highthon_10th.module.main.MainRoute
import com.example.android_highthon_10th.module.main.chat.ChatRoute
import com.example.android_highthon_10th.module.main.explore.ExploreRoute
import com.example.android_highthon_10th.module.main.profile.ProfileRoute
import com.example.android_highthon_10th.module.main.task.TaskRoute
import com.example.android_highthon_10th.module.splash.SplashRoute

object AppRoute {
    const val MAIN_ROUTE = "main-route"

    const val SPLASH = "splash"
    const val MAIN = "main"

    const val MAIN_EXPLORE = "$MAIN/explore"
    const val MAIN_CHAT = "$MAIN/chat"
    const val MAIN_TASK = "$MAIN/task"
    const val MAIN_PROFILE = "$MAIN/profile"
}


@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.SPLASH,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(
            route = AppRoute.SPLASH
        ) {
            SplashRoute(
                navigateMain = {
                    navController.navigate(AppRoute.MAIN_ROUTE) {
                        popUpTo(AppRoute.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        navigation(
            route = AppRoute.MAIN_ROUTE,
            startDestination = AppRoute.MAIN
        ) {
            composable(
                route = AppRoute.MAIN
            ) {
                val bottomNavController = rememberNavController()

                MainRoute(
                    bottomNavController = bottomNavController,
                    startDestination = AppRoute.MAIN_EXPLORE
                ) {
                    composable(
                        route = AppRoute.MAIN_EXPLORE
                    ) {
                        ExploreRoute()
                    }
                    composable(
                        route = AppRoute.MAIN_CHAT
                    ) {
                        ChatRoute()
                    }
                    composable(
                        route = AppRoute.MAIN_TASK
                    ) {
                        TaskRoute()
                    }
                    composable(
                        route = AppRoute.MAIN_PROFILE
                    ) {
                        ProfileRoute()
                    }
                }
            }
        }
    }
}