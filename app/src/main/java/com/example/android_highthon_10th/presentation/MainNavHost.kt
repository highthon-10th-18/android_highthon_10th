package com.example.android_highthon_10th.presentation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_highthon_10th.data.model.response.AlarmResponse
import com.example.android_highthon_10th.data.model.response.TodoResponse
import com.example.android_highthon_10th.presentation.main.MainRoute
import com.example.android_highthon_10th.presentation.main.chat.ChatRoomRoute
import com.example.android_highthon_10th.presentation.main.chat.ChatRoute
import com.example.android_highthon_10th.presentation.main.explore.ExploreRoute
import com.example.android_highthon_10th.presentation.main.profile.ProfileRoute
import com.example.android_highthon_10th.presentation.main.task.TaskRoute
import com.example.android_highthon_10th.presentation.main.task.add.TaskAddRoute
import com.example.android_highthon_10th.presentation.main.task.detail.TaskDetailRoute
import com.example.android_highthon_10th.presentation.onboard.LoginRoute
import com.example.android_highthon_10th.presentation.onboard.OnboardRoute
import com.example.android_highthon_10th.presentation.onboard.SignUpRoute
import com.example.android_highthon_10th.presentation.splash.SplashRoute
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object AppRoute {
    const val MAIN_ROUTE = "main-route"
    const val ONBOARD_ROUTE = "onboard-route"

    const val SPLASH = "splash"
    const val MAIN = "main"

    const val MAIN_EXPLORE = "$MAIN/explore"
    const val MAIN_CHAT = "$MAIN/chat"
    const val MAIN_TASK = "$MAIN/task"
    const val MAIN_PROFILE = "$MAIN/profile"

    const val ONBOARD = "onboard"
    const val ONBOARD_LOGIN = "$ONBOARD/login"
    const val ONBOARD_SIGNUP = "$ONBOARD/signup"

    const val TASK_DETAIL = "$MAIN_TASK/detail"
    const val TASK_ADD= "$MAIN_TASK/add"

    const val MAIN_CHAT_ROOM = "$MAIN/chat/room"

}


@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {
    val gson = Gson()

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
                navigateOnboard = {
                    navController.navigate(AppRoute.ONBOARD_ROUTE) {
                        popUpTo(AppRoute.SPLASH) {
                            inclusive = true
                        }
                    }
                },
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
            route = AppRoute.ONBOARD_ROUTE,
            startDestination = AppRoute.ONBOARD
        ) {
            composable(
                route = AppRoute.ONBOARD
            ) {
                OnboardRoute(
                    navigateLogin = {
                        navController.navigate(AppRoute.ONBOARD_LOGIN)
                    },
                    navigateSignUp = {
                        navController.navigate(AppRoute.ONBOARD_SIGNUP)
                    }
                )
            }
            composable(
                route = AppRoute.ONBOARD_LOGIN
            ) {
                LoginRoute(
                    navigateMain = {
                        navController.navigate(AppRoute.MAIN_ROUTE) {
                            popUpTo(AppRoute.ONBOARD_ROUTE) {
                                inclusive = true
                            }
                        }
                    },
                    popBackStack = navController::navigateUp
                )
            }
            composable(
                route = AppRoute.ONBOARD_SIGNUP
            ) {
                SignUpRoute(
                    navigateMain = {
                        navController.navigate(AppRoute.MAIN_ROUTE) {
                            popUpTo(AppRoute.ONBOARD_ROUTE) {
                                inclusive = true
                            }
                        }
                    },
                    popBackStack = navController::navigateUp
                )
            }
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
                        ChatRoute(
                            navigate = {
                                navController.navigate(AppRoute.MAIN_CHAT_ROOM)
                            }
                        )
                    }
                    composable(
                        route = AppRoute.MAIN_TASK
                    ) {
                        TaskRoute(
                            navigateEdit = { alarm, todo ->
                                val alarmJson = URLEncoder.encode(gson.toJson(alarm), StandardCharsets.UTF_8.toString())
                                val todoJson = URLEncoder.encode(gson.toJson(todo), StandardCharsets.UTF_8.toString())

                                navController.navigate(AppRoute.TASK_DETAIL + "?alarm=$alarmJson&todo=$todoJson")
                            },
                            navigatePlus = {
                                Log.d("확인", "!!!")
                                navController.navigate(AppRoute.TASK_ADD)
                            }
                        )
                    }
                    composable(
                        route = AppRoute.MAIN_PROFILE
                    ) {
                        ProfileRoute()
                    }
                }
            }

            composable(
                route = AppRoute.TASK_DETAIL + "?alarm={alarm}&todo={todo}",
                arguments = listOf(
                    navArgument("alarm") { type = NavType.StringType; nullable = true },
                    navArgument("todo") { type = NavType.StringType; nullable = true }
                )
            ) { backStackEntry ->

                val alarmJson = backStackEntry.arguments?.getString("alarm")?.let {
                    URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                }
                val todoJson = backStackEntry.arguments?.getString("todo")?.let {
                    URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
                }

                val alarmItem = alarmJson?.let { gson.fromJson(it, AlarmResponse::class.java) }
                val todoItem = todoJson?.let { gson.fromJson(it, TodoResponse::class.java) }

                TaskDetailRoute(
                    alarmItem = alarmItem,
                    todoItem = todoItem,
                    popBackStack = navController::navigateUp
                )
            }

            composable(
                route = AppRoute.TASK_ADD
            ) {
                TaskAddRoute (
                    popBackStack = navController::navigateUp
                )
            }

            composable(
                route = AppRoute.MAIN_CHAT_ROOM
            ) {

                ChatRoomRoute (
                    popBackStack = navController::navigateUp
                )
            }
        }
    }
}