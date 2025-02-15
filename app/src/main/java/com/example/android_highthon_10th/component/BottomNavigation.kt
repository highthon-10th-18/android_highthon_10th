package com.example.android_highthon_10th.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.android_highthon_10th.AppRoute
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.noRippleClickable

enum class MainBottomRoute(
    val route: String,
    val text: String,
    val icon: Int,
) {
    Item1 (
        route = AppRoute.MAIN_ITEM1,
        text = "예시 문구",
        icon = R.drawable.ic_default_size_icon,
    ),
    Item2 (
        route = AppRoute.MAIN_ITEM2,
        text = "예시 문구",
        icon = R.drawable.ic_default_size_icon,
    ),
    Item3 (
        route = AppRoute.MAIN_ITEM3,
        text = "예시 문구",
        icon = R.drawable.ic_default_size_icon,
    ),
    Item4 (
        route = AppRoute.MAIN_ITEM4,
        text = "예시 문구",
        icon = R.drawable.ic_default_size_icon,
    ),
    Item5 (
        route = AppRoute.MAIN_ITEM5,
        text = "예시 문구",
        icon = R.drawable.ic_default_size_icon,
    )
}

@Composable
private fun RowScope.NavigationItem(
    item: MainBottomRoute,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(ColorStyles.BgBase.elevated)
            .navigationBarsPadding()
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                type = IconSizeType.Size24,
                res = item.icon,
                tint = if (isSelected) ColorStyles.CntDefault.primary else ColorStyles.CntStatus.unselected
            )
            Text(
                text = item.text,
                color = if (isSelected) ColorStyles.CntDefault.primary else ColorStyles.CntStatus.unselected,
                style = if (isSelected) TextStyles.caption.strong else TextStyles.caption.default
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    bottomNavController: NavController,
) {
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorStyles.BgBase.elevated)
            .navigationBarsPadding()
            .height(52.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainBottomRoute.entries.forEach { item ->
            NavigationItem(
                item = item,
                isSelected = currentRoute == item.route,
                onClick = {
                    bottomNavController.navigate(item.route) {
                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}