package com.example.android_highthon_10th.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Width(width: Int) {
    Spacer(Modifier.width(width.dp))
}

@Composable
fun Width(width: Double) {
    Spacer(Modifier.width(width.dp))
}

@Composable
fun Height(height: Int) {
    Spacer(Modifier.height(height.dp))
}

@Composable
fun Height(height: Double) {
    Spacer(Modifier.height(height.dp))
}

@Composable
fun ColumnScope.Flex(flex: Float = 1f) {
    Spacer(Modifier.weight(flex))
}

@Composable
fun RowScope.Flex(flex: Float = 1f) {
    Spacer(Modifier.weight(flex))
}