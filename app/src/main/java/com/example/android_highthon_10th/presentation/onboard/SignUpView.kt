package com.example.android_highthon_10th.presentation.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.presentation.component.Flex
import com.example.android_highthon_10th.presentation.component.Height
import com.example.android_highthon_10th.presentation.component.IconButton
import com.example.android_highthon_10th.presentation.component.TextField
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateMain: () -> Unit,
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.successSignUp.collectLatest {
            navigateMain()
        }
    }

    SignUpScreen(
        state = state,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onNameChanged = viewModel::onNameChanged,
        signUp = viewModel::signUp,
        popBackStack = popBackStack
    )
}

@Composable
private fun SignUpScreen(
    state: SignUpViewModel.State = SignUpViewModel.State(),
    onEmailChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onNameChanged: (String) -> Unit = {},
    signUp: () -> Unit = {},
    popBackStack: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorStyles.BgBase.default)
                .padding(paddingValues)
        ){
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton (
                    res = R.drawable.ic_left_arrow,
                    iconAlignment = Alignment.CenterStart,
                    onClick = popBackStack
                )
            }
            Height(48)
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                text = "계정 정보를 입력하여\n" + "회원가입해주세요",
                style = TextStyles.display.strong,
                color = ColorStyles.CntDefault.primary
            )
            Height(32)
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                labelText = "이메일",
                placeholderText = "user@example.com",
                value = state.email,
                onValueChanged = onEmailChanged
            )
            Height(8)
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                isPassword = true,
                labelText = "비밀번호",
                placeholderText = "비밀번호를 입력해주세요",
                value = state.password,
                onValueChanged = onPasswordChanged
            )
            Height(8)
            TextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                labelText = "이름",
                placeholderText = "홍길동",
                value = state.name,
                onValueChanged = onNameChanged
            )
            Flex()
            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = ColorChip.white),
                shape = RoundedCornerShape(12.dp),
                onClick = signUp
            ) {
                Text(
                    text = "회원가입",
                    color = ColorChip.black,
                    style = TextStyles.title.default
                )
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    AppTheme {
        SignUpScreen()
    }
}