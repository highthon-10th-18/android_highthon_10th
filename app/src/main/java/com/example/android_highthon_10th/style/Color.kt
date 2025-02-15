package com.example.android_highthon_10th.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object ColorChip {
    val primary = Color(0xFF007AFF)

    val black = Color(0xFF000000)
    val white = Color(0xffffffff)

    val dim = black.copy(alpha = 0.4f)
    val dimAccent = black.copy(alpha = 0.7f)

    val gray60 = Color(0xffa4a4b5)
    val gray100 = Color(0xfff4f4f6)
    val gray200 = Color(0xffe7e7e9)
    val gray300 = Color(0xffcfcfda)
    val gray400 = Color(0xffb2b2bd)
    val gray500 = Color(0xff9797a0)
    val gray600 = Color(0xff777783)
    val gray700 = Color(0xff5a5a66)
    val gray800 = Color(0xff1f1f28)
    val gray900 = Color(0xff101016)

    val red100 = Color(0xffffd6d2)
    val red200 = Color(0xffff9c98)
    val red300 = Color(0xffff766e)
    val red400 = Color(0xffff564b)
    val red500 = Color(0xffff3b30)
    val red600 = Color(0xffb2231a)
    val red700 = Color(0xff850700)
    val red800 = Color(0xff5c0500)
    val red900 = Color(0xff4d0000)

    val yellow100 = Color(0xfffff3c2)
    val yellow200 = Color(0xffffe270)
    val yellow300 = Color(0xffffda47)
    val yellow400 = Color(0xffffd21f)
    val yellow500 = Color(0xfff5c400)
    val yellow600 = Color(0xffcca300)
    val yellow700 = Color(0xffa38300)
    val yellow800 = Color(0xff7a6200)
    val yellow900 = Color(0xff524002)

    val green100 = Color(0xffd5faed)
    val green200 = Color(0xffcbf5e3)
    val green300 = Color(0xff7cf4d7)
    val green400 = Color(0xff1bebb3)
    val green500 = Color(0xff34ca94)
    val green600 = Color(0xff00b887)
    val green700 = Color(0xff008561)
    val green800 = Color(0xff00523c)
    val green900 = Color(0xff003b31)

    val blue100 = Color(0xfff0f0ff)
    val blue200 = Color(0xffc6eef8)
    val blue300 = Color(0xff73b6ff)
    val blue400 = Color(0xff3998ff)
    val blue500 = Color(0xff007aff)
    val blue600 = Color(0xff0062cc)
    val blue700 = Color(0xff004999)
    val blue800 = Color(0xff003166)
    val blue900 = Color(0xff021738)
}

data class BaseColor(
    val default: Color,
    val defaultPressed: Color,
    val elevated: Color,
    val elevatedPressed: Color,
    val border: Color
)

data class WaringColor(
    val default: Color,
    val defaultHover: Color,
    val elevated: Color,
    val elevatedPressed: Color,
    val border: Color
)

data class DefaultColor(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val quaternary: Color
)

data class StatusColor(
    val unselected: Color,
    val unable: Color,
    val accent: Color,
    val positive: Color,
    val negative: Color,
    val warning: Color
)

object ColorStyles {
    val bgBaseLight = BaseColor(
        default = ColorChip.gray100,
        defaultPressed = ColorChip.gray200,
        elevated = ColorChip.white,
        elevatedPressed = ColorChip.gray100,
        border = ColorChip.gray200
    )

    val bgBaseDark = BaseColor(
        default = ColorChip.black,
        defaultPressed = ColorChip.gray900,
        elevated = ColorChip.gray900,
        elevatedPressed = ColorChip.gray800,
        border = ColorChip.gray800
    )

    val bgAccentLight = BaseColor(
        default = ColorChip.blue500,
        defaultPressed = ColorChip.blue600,
        elevated = ColorChip.blue100,
        elevatedPressed = ColorChip.blue200,
        border = ColorChip.blue600
    )

    val bgAccentDark = BaseColor(
        default = ColorChip.blue500,
        defaultPressed = ColorChip.blue400,
        elevated = ColorChip.blue900,
        elevatedPressed = ColorChip.blue800,
        border = ColorChip.blue400
    )

    val bgPositiveLight = BaseColor(
        default = ColorChip.green500,
        defaultPressed = ColorChip.green600,
        elevated = ColorChip.green100,
        elevatedPressed = ColorChip.green200,
        border = ColorChip.green600
    )

    val bgPositiveDark = BaseColor(
        default = ColorChip.green500,
        defaultPressed = ColorChip.green400,
        elevated = ColorChip.green900,
        elevatedPressed = ColorChip.green800,
        border = ColorChip.green600
    )

    val bgNegativeLight = BaseColor(
        default = ColorChip.red500,
        defaultPressed = ColorChip.red600,
        elevated = ColorChip.red100,
        elevatedPressed = ColorChip.red200,
        border = ColorChip.red600
    )

    val bgNegativeDark = BaseColor(
        default = ColorChip.red500,
        defaultPressed = ColorChip.red400,
        elevated = ColorChip.red900,
        elevatedPressed = ColorChip.red800,
        border = ColorChip.red600
    )

    val bgWaringLight = WaringColor(
        default = ColorChip.yellow500,
        defaultHover = ColorChip.yellow600,
        elevated = ColorChip.yellow100,
        elevatedPressed = ColorChip.yellow200,
        border = ColorChip.yellow600
    )

    val bgWaringDark = WaringColor(
        default = ColorChip.yellow500,
        defaultHover = ColorChip.yellow400,
        elevated = ColorChip.yellow900,
        elevatedPressed = ColorChip.yellow800,
        border = ColorChip.yellow600
    )

    val cntDefaultLight = DefaultColor(
        primary = ColorChip.gray900,
        secondary = ColorChip.gray700,
        tertiary = ColorChip.gray600,
        quaternary = ColorChip.gray500
    )

    val cntDefaultDark = DefaultColor(
        primary = ColorChip.gray100,
        secondary = ColorChip.gray300,
        tertiary = ColorChip.gray400,
        quaternary = ColorChip.gray600
    )

    val cntStatusLight = StatusColor(
        unselected = ColorChip.gray400,
        unable = ColorChip.gray300,
        accent = ColorChip.blue500,
        positive = ColorChip.green500,
        negative = ColorChip.red500,
        warning = ColorChip.yellow500
    )

    val cntStatusDark = StatusColor(
        unselected = ColorChip.gray600,
        unable = ColorChip.gray700,
        accent = ColorChip.blue500,
        positive = ColorChip.green500,
        negative = ColorChip.red500,
        warning = ColorChip.yellow500
    )

    private val localBgBaseLight = staticCompositionLocalOf { bgBaseLight }
    private val localBgAccentLight = staticCompositionLocalOf { bgAccentLight }
    private val localBgPositiveLight = staticCompositionLocalOf { bgPositiveLight }
    private val localBgNegativeLight = staticCompositionLocalOf { bgNegativeLight }
    private val localBgWaringLight = staticCompositionLocalOf { bgWaringLight }
    private val localCntDefaultLight = staticCompositionLocalOf { cntDefaultLight }
    private val localCntStatusLight = staticCompositionLocalOf { cntStatusLight }

    private val localBgBaseDark = staticCompositionLocalOf { bgBaseDark }
    private val localBgAccentDark = staticCompositionLocalOf { bgAccentDark }
    private val localBgPositiveDark = staticCompositionLocalOf { bgPositiveDark }
    private val localBgNegativeDark = staticCompositionLocalOf { bgNegativeDark }
    private val localBgWaringDark = staticCompositionLocalOf { bgWaringDark }
    private val localCntDefaultDark = staticCompositionLocalOf { cntDefaultDark }
    private val localCntStatusDark = staticCompositionLocalOf { cntStatusDark }

    val BgBase: BaseColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localBgBaseDark.current else localBgBaseLight.current
        }
    val BgAccent: BaseColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localBgAccentDark.current else localBgAccentLight.current
        }
    val BgPositive: BaseColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localBgPositiveDark.current else localBgPositiveLight.current
        }
    val BgNegative: BaseColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localBgNegativeDark.current else localBgNegativeLight.current
        }
    val BgWaring: WaringColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localBgWaringDark.current else localBgWaringLight.current
        }
    val CntDefault: DefaultColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localCntDefaultDark.current else localCntDefaultLight.current
        }
    val CntStatus: StatusColor
        @Composable
        get() {
            return if (isSystemInDarkTheme()) localCntStatusDark.current else localCntStatusLight.current
        }
}