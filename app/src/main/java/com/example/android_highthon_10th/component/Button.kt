package com.example.android_highthon_10th.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme

@Composable
fun IconButton(
    buttonSize: Dp = 40.dp,
    iconSize: Dp = 24.dp,
    iconAlignment: Alignment = Alignment.Center,
    tint: Color? = null,
    res: Int,
    onClick: () -> Unit
) {
    val modifier = Modifier
        .size(buttonSize)
        .clickable { onClick() }
    Box(
        modifier = modifier,
        contentAlignment = iconAlignment
    ) {
        if (tint != null) {
            Icon(
                painter = painterResource(res),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = tint
            )
        } else {
            Icon(
                painter = painterResource(res),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column {
            IconButton(res = R.drawable.ic_default_size_icon) { }
        }
    }
}