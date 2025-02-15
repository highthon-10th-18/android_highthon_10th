package com.example.android_highthon_10th.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

@Composable
fun SectionTitle(
    isDivider: Boolean,
    title: String,
    backPressed: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyles.heading.strong,
            color = ColorStyles.CntDefault.primary
        )
        Width(8)
        if (isDivider) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 1.dp,
                color = ColorStyles.BgBase.border
            )
        } else {
            Flex()
        }
        backPressed?.let {
            Width(8)
            IconButton(
                res = R.drawable.ic_right_arrow,
                onClick = it
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    val title = "수령 가능 물품"

    AppTheme {
        Column(Modifier.padding(10.dp)) {
            SectionTitle(true, title)
            Height(20)
            SectionTitle(false, title)
            Height(20)
            SectionTitle(true, title) { }
            Height(20)
            SectionTitle(false, title) { }
        }
    }
}