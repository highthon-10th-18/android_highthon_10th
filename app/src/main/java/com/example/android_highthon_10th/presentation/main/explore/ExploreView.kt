package com.example.android_highthon_10th.presentation.main.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.presentation.component.IconButton
import com.example.android_highthon_10th.presentation.component.IconSizeType
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorStyles

@Composable
fun ExploreRoute() {

    ExploreScreen(

    )
}

@Composable
private fun ExploreScreen(
    navigateSearch: () -> Unit = { }
) {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton (
                    res = R.drawable.ic_search,
                    onClick = navigateSearch
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ExploreScreen()
    }
}