package com.example.android_highthon_10th

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.android_highthon_10th.module.MainRoute
import com.example.android_highthon_10th.module.main.Item1Route
import com.example.android_highthon_10th.module.main.Item2Route
import com.example.android_highthon_10th.module.main.Item3Route
import com.example.android_highthon_10th.module.main.Item4Route
import com.example.android_highthon_10th.module.main.Item5Route
import com.example.android_highthon_10th.module.splash.SplashRoute

object AppRoute {
    const val MAIN_ROUTE = "main-route"

    const val SPLASH = "splash"
    const val MAIN = "main"

    const val MAIN_ITEM1 = "$MAIN/item1"
    const val MAIN_ITEM2 = "$MAIN/item2"
    const val MAIN_ITEM3 = "$MAIN/item3"
    const val MAIN_ITEM4 = "$MAIN/item4"
    const val MAIN_ITEM5 = "$MAIN/item5"
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
                    startDestination = AppRoute.MAIN_ITEM1
                ) {
                    composable(
                        route = AppRoute.MAIN_ITEM1
                    ) {
                        Item1Route()
                    }
                    composable(
                        route = AppRoute.MAIN_ITEM2
                    ) {
                        Item2Route()
                    }
                    composable(
                        route = AppRoute.MAIN_ITEM3
                    ) {
                        Item3Route()
                    }
                    composable(
                        route = AppRoute.MAIN_ITEM4
                    ) {
                        Item4Route()
                    }
                    composable(
                        route = AppRoute.MAIN_ITEM5
                    ) {
                        Item5Route()
                    }
                }
            }
        }
    }
}