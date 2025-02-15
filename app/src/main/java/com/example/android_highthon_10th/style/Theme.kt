package com.example.android_highthon_10th.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

internal val LightColorScheme = lightColorScheme(
    background = ColorChip.white,
//    primary = ,
//    secondary = ,
//    tertiary = ,
)

internal val DarkColorScheme = darkColorScheme(
    background = ColorChip.black,
//    primary = ,
//    secondary = ,
//    tertiary = ,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val darkTheme = isSystemInDarkTheme()
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalDensity provides Density(
            density = LocalDensity.current.density,
            fontScale = 1f
        )
    ) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}