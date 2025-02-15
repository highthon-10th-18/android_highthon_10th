package com.example.android_highthon_10th.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip

@Composable
fun Switch(
    isChecked: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {
    val thumbOffset by animateFloatAsState(
        targetValue = if (isChecked) 24f else 4f, label = ""
    )

    Box(
        modifier = Modifier
            .size(52.dp, 32.dp)
            .background(
                color = if (isChecked) ColorChip.primary else ColorChip.gray60,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { onCheckChanged(!isChecked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .offset(x = thumbOffset.dp)
                .background(Color.White, CircleShape)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column(Modifier.padding(10.dp)) {
            Switch(true) { }
            Height(20)
            Switch(false){ }
        }
    }
}