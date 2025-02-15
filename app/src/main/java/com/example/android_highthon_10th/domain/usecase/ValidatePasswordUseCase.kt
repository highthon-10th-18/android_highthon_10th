package com.example.android_highthon_10th.domain.usecase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
        return passwordRegex.matches(password)
    }
}