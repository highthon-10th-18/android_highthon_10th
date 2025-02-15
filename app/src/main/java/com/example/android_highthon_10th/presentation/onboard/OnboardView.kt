package com.example.android_highthon_10th.presentation.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles

@Composable
fun OnboardRoute(
    navigateLogin: () -> Unit,
    navigateSignUp: () -> Unit
) {
    OnboardScreen(
        navigateLogin = navigateLogin,
        navigateSignUp = navigateSignUp
    )
}

@Composable
private fun OnboardScreen(
    navigateLogin: () -> Unit = {},
    navigateSignUp: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ){
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {

            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors().copy(containerColor = ColorChip.white),
                    shape = RoundedCornerShape(12.dp),
                    onClick = navigateLogin
                ) {
                    Text(
                        text = "로그인",
                        color = ColorChip.black,
                        style = TextStyles.title.default
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors().copy(containerColor = ColorChip.black),
                    shape = RoundedCornerShape(12.dp),
                    onClick = navigateSignUp
                ) {
                    Text(
                        text = "회원가입",
                        color = ColorChip.white,
                        style = TextStyles.title.default
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        OnboardScreen()
    }
}