package com.example.android_highthon_10th.style

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.android_highthon_10th.R

private val pretendard = FontFamily(
    Font(R.font.pretendard_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.pretendard_medium, weight = FontWeight.Medium),
)

class Style(base: TextStyle) {
    val default: TextStyle = base.copy(fontWeight = FontWeight.Medium)
    val strong: TextStyle = base.copy(fontWeight = FontWeight.SemiBold)
}

object TextStyles {
    val caption = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.30000001192092896).sp
        )
    )
    val label = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = (-0.30000001192092896).sp
        )
    )
    val footnote = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.4000000059604645).sp
        )
    )
    val body = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.4000000059604645).sp
        )
    )
    val title = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.5).sp
        )
    )
    val heading = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 17.sp,
            lineHeight = 26.sp,
            letterSpacing = (-0.5).sp
        )
    )
    val headLine = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.800000011920929).sp
        )
    )
    val display = Style(
        TextStyle(
            fontFamily = pretendard,
            fontSize = 28.sp,
            lineHeight = 40.sp,
            letterSpacing = (-1).sp
        )
    )
}