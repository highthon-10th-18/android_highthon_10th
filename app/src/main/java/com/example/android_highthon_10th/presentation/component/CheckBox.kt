package com.example.android_highthon_10th.presentation.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip
import com.example.android_highthon_10th.style.ColorStyles

@Composable
fun CheckBox(
    checked: Boolean,
    onClick: () -> Unit,
) {
    Crossfade(
        targetState = checked,
        label = "",
        animationSpec = TweenSpec(durationMillis = 100, easing = LinearEasing)
    ) { selected ->
        val backgroundColor = if (selected) ColorChip.primary else ColorStyles.BgBase.default
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(backgroundColor, RoundedCornerShape(4.dp))
                .clickable { onClick() }
                .then(
                    if (!selected) {
                        Modifier.border(BorderStroke(1.dp, ColorStyles.CntStatus.unselected), RoundedCornerShape(4.dp))
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            val iconColor = if (selected) ColorChip.white else ColorStyles.CntStatus.unselected

            Image(
                modifier = Modifier.size(14.97.dp, 10.64.dp),
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column(Modifier.padding(10.dp)) {
            CheckBox(true) { }
            Height(20)
            CheckBox(false) { }
        }
    }
}