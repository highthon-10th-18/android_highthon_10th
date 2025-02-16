package com.example.android_highthon_10th.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme

sealed class RatioImageType(val ratio: Float) {
    data object Ratio16to9 : RatioImageType(16f / 9f)
    data object Ratio4to3 : RatioImageType( 4f / 3f)
    data object Ratio1to1 : RatioImageType( 1f)
}

@Composable
fun CircleImage(
    url: String,
    size: Dp = 16.dp,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = Modifier.size(size).clip(CircleShape)
    )
}

@Composable
fun RatioImage(
    modifier: Modifier = Modifier,
    type: RatioImageType,
    res: Int,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Image(
        painter = painterResource(res),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .aspectRatio(type.ratio)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun RatioImage(
    modifier: Modifier = Modifier,
    type: RatioImageType,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .aspectRatio(type.ratio)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Preview(showBackground = true, backgroundColor = 0xff000000, heightDp = 1000)
@Composable
private fun Preview() {
    AppTheme {
        Column(Modifier.padding(20.dp)) {
            RatioImage(
                modifier = Modifier.fillMaxWidth(),
                type = RatioImageType.Ratio1to1,
                res = R.drawable.ic_launcher_background
            )
            Height(20)
            RatioImage(
                modifier = Modifier.fillMaxWidth(),
                type = RatioImageType.Ratio4to3,
                res = R.drawable.ic_launcher_background
            )
            Height(20)
            RatioImage(
                modifier = Modifier.fillMaxWidth(),
                type = RatioImageType.Ratio16to9,
                res = R.drawable.ic_launcher_background
            )
        }
    }
}