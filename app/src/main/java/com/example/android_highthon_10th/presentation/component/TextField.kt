package com.example.android_highthon_10th.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_highthon_10th.R
import com.example.android_highthon_10th.style.AppTheme
import com.example.android_highthon_10th.style.ColorChip
import com.example.android_highthon_10th.style.ColorStyles
import com.example.android_highthon_10th.style.TextStyles
import com.example.android_highthon_10th.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    labelText: String,
    placeholderText: String,
    guideText: String? = null,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val background = if (isError) ColorStyles.BgNegative.elevated else ColorStyles.BgBase.elevated
    val textColor = if (isEnabled) ColorStyles.CntDefault.primary else ColorStyles.CntStatus.unable

    var showText by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (isPassword) showText = false
    }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        enabled = isEnabled,
        singleLine = isSingleLine,
        textStyle = TextStyles.title.default.copy(color = textColor),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = if (!showText) {
            PasswordVisualTransformation()
        } else {
            visualTransformation
        },
        interactionSource = interactionSource
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Box {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(66.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(background)
                        .border(
                            width = 1.dp,
                            color = if (isError) ColorStyles.BgNegative.border else ColorStyles.BgBase.border,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    labelText.let {
                        val labelTextColor = when {
                            isError -> ColorStyles.CntStatus.negative
                            !isEnabled -> ColorStyles.CntStatus.unable
                            else -> ColorStyles.CntDefault.quaternary
                        }
                        Text(
                            text = it,
                            style = TextStyles.label.default,
                            color = labelTextColor,
                            maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
                        )
                    }
                    TextFieldDefaults.DecorationBox(
                        value = value,
                        innerTextField = { Box(modifier = Modifier.fillMaxWidth()) { it() } },
                        enabled = isEnabled,
                        singleLine = isSingleLine,
                        visualTransformation = if (!showText) {
                            PasswordVisualTransformation()
                        } else {
                            visualTransformation
                        },
                        interactionSource = interactionSource,
                        placeholder = {
                            val placeholderTextColor = if (isEnabled) {
                                ColorStyles.CntStatus.unselected
                            } else {
                                ColorStyles.CntStatus.unable
                            }
                            Text(
                                text = placeholderText,
                                style = TextStyles.title.default,
                                color = placeholderTextColor,
                                maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
                            )
                        },
                        contentPadding = PaddingValues(
                            vertical = 0.dp,
                            horizontal = 0.dp
                        ),
                        colors = TextFieldDefaults.colors(
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = background,
                            unfocusedContainerColor = background,
                            disabledContainerColor = background,
                            cursorColor = ColorChip.primary,
                        )
                    )
                }
                if (isPassword) {
                    IconButton(
                        iconAlignment = Alignment.BottomEnd,
                        modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp).noRippleClickable { showText = !showText },
                        tint = ColorStyles.CntDefault.quaternary,
                        res = if (showText) R.drawable.ic_password_on else R.drawable.ic_password_off,
                        onClick = { showText = !showText }
                    )
                }
            }

            guideText?.let {
                val guideTextColor = when {
                    isError -> ColorStyles.CntStatus.negative
                    !isEnabled -> ColorStyles.CntStatus.unable
                    else -> ColorStyles.CntDefault.secondary
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = it,
                    color = guideTextColor,
                    style = TextStyles.footnote.default
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            TextField(
                value = "",
                onValueChanged = {},
                placeholderText = "힌트 텍스트",
                labelText = "라벨 텍스트",
                guideText = "힌트 텍스트",
            )
            Height(20)

            TextField(
                value = "힌트 텍스트",
                onValueChanged = {},
                placeholderText = "힌트 텍스트",
                labelText = "라벨 텍스트",
                guideText = "힌트 텍스트",
            )
            Height(20)

            TextField(
                isError = true,
                value = "힌트 텍스트",
                onValueChanged = {},
                placeholderText = "힌트 텍스트",
                labelText = "라벨 텍스트",
                guideText = "힌트 텍스트",
            )
            Height(20)

            TextField(
                isEnabled = false,
                value = "",
                onValueChanged = {},
                placeholderText = "힌트 텍스트",
                labelText = "라벨 텍스트",
                guideText = "힌트 텍스트",
            )
            Height(20)

            TextField(
                isPassword = true,
                value = "힌트 텍스트",
                onValueChanged = {},
                placeholderText = "힌트 텍스트",
                labelText = "라벨 텍스트",
                guideText = "힌트 텍스트",
            )
            Height(20)

        }
    }
}