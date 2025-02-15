package com.example.android_highthon_10th.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme

sealed class IconSizeType(
    val size: Dp,
) {
    data object Size16: IconSizeType(size = 16.dp)
    data object Size20: IconSizeType(size = 20.dp)
    data object Size24: IconSizeType(size = 24.dp)
    data object Size32: IconSizeType(size = 32.dp)
    data object Size48: IconSizeType(size = 48.dp)
    data object Size64: IconSizeType(size = 64.dp)
}

@Composable
fun Icon(type: IconSizeType, res: Int, tint: Color = Color.Unspecified) {
    Icon(
        painter = painterResource(res),
        contentDescription = null,
        modifier = Modifier.size(type.size),
        tint = tint
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column {
            Icon(
                type = IconSizeType.Size16,
                res = R.drawable.ic_default_size_icon
            )
            Height(20)
            Icon(
                type = IconSizeType.Size20,
                res = R.drawable.ic_default_size_icon
            )
            Height(20)
            Icon(
                type = IconSizeType.Size24,
                res = R.drawable.ic_default_size_icon
            )
            Height(20)
            Icon(
                type = IconSizeType.Size32,
                res = R.drawable.ic_default_size_icon
            )
            Height(20)
            Icon(
                type = IconSizeType.Size48,
                res = R.drawable.ic_default_size_icon
            )
            Height(20)
            Icon(
                type = IconSizeType.Size64,
                res = R.drawable.ic_default_size_icon
            )
        }
    }
}