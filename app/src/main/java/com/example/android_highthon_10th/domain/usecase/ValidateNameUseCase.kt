package com.example.android_highthon_10th.domain.usecase

class ValidateNameUseCase {
    operator fun invoke(name: String): Boolean {
        val nameReplaceRegex = Regex("[^ㄱ-ㅣ가-힣a-zA-Zㆍᆢ]")
        return nameReplaceRegex.matches(name) && name.isNotBlank()
    }
}
