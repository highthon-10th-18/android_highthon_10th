package com.example.android_highthon_10th.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

data class HeaderButton(
    val res: Int,
    val onClick: () -> Unit
)

@Composable
fun Header(
    title: String,
    isDisplay: Boolean = false,
    headerButton1: HeaderButton? = null,
    headerButton2: HeaderButton? = null,
    backPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Width(16)
            IconButton(
                tint = ColorStyles.CntDefault.quaternary,
                res = R.drawable.ic_left_arrow,
                iconAlignment = Alignment.CenterStart,
                onClick = { backPressed() }
            )
            if (isDisplay) {
                Flex()
            } else {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    color = ColorStyles.CntDefault.primary,
                    style = TextStyles.heading.strong,
                    textAlign = TextAlign.Start
                )
            }
            headerButton1?.let {
                IconButton(
                    res = it.res,
                    onClick = { it.onClick }
                )
            }
            headerButton2?.let {
                IconButton(
                    res = it.res,
                    onClick = { it.onClick }
                )
            }
            Width(8)
        }
        if (isDisplay) {
            Height(48)
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = title,
                color = ColorStyles.CntDefault.primary,
                style = TextStyles.display.strong,
                textAlign = TextAlign.Start
            )
            Height(16)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column {
            Header(
                title = "Example Text",
                isDisplay = true,
                headerButton1 = HeaderButton(R.drawable.ic_default_size_icon) {},
                headerButton2 = HeaderButton(R.drawable.ic_default_size_icon) {},
                backPressed = {}
            )
            Height(20)
            Header(
                title = "Example Text",
                isDisplay = false,
                headerButton1 = HeaderButton(R.drawable.ic_default_size_icon) {},
                headerButton2 = HeaderButton(R.drawable.ic_default_size_icon) {},
                backPressed = {}
            )
        }
    }
}