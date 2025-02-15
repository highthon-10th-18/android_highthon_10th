package com.example.android_highthon_10th.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip

@Composable
fun RadioButton(checked: Boolean, onClick: () -> Unit) {
    Crossfade(
        targetState = checked,
        label = "",
        animationSpec = TweenSpec(durationMillis = 100, easing = LinearEasing)
    ) { selected ->
        Image(
            contentDescription = null,
            colorFilter = if (selected) {
                ColorFilter.tint(ColorChip.primary)
            } else {
                null
            },
            painter = painterResource(
                if (selected) R.drawable.ic_radio_enable
                else R.drawable.ic_radio_disable
            ),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .clickable { onClick() }
        )
    }
}




@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column {
            RadioButton(true) { }
            Height(20)
            RadioButton(false) { }
        }
    }
}