package com.example.android_highthon_10th.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.dropShadow
import com.example.android_highthon_10th.util.noRippleClickable

@Composable
fun SegmentController(
    textList: List<String>
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val radius = RoundedCornerShape(8.dp)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorStyles.BgBase.defaultPressed, RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        val density = LocalDensity.current

        val segmentWidth = constraints.maxWidth / textList.size
        val segmentWidthDp = with(density) { segmentWidth.toDp() }

        val animatedX by animateFloatAsState(targetValue = selectedIndex * segmentWidthDp.value, label = "")

        Box(
            modifier = Modifier
                .offset(x = animatedX.dp)
                .dropShadow(radius)
                .height(42.dp)
                .width(segmentWidthDp)
                .clip(radius)
                .background(ColorStyles.BgBase.elevated, radius)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            textList.forEachIndexed { index, text ->
                Segment(
                    text = text,
                    isSelected = index == selectedIndex,
                    onClick = { selectedIndex = index },
                    modifier = Modifier.width(segmentWidthDp)
                )
            }
        }
    }
}

@Composable
fun Segment(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(42.dp)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = if (isSelected) TextStyles.body.strong else TextStyles.body.default,
            color = if (isSelected) ColorStyles.CntDefault.primary else ColorStyles.CntStatus.unselected
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun Preview() {
    AppTheme {
        Column(modifier = Modifier.padding(10.dp)) {
            val list = List(3) { "선택${it + 1}" }
            SegmentController(list)
        }
    }
}