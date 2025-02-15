package com.example.android_highthon_10th.util

import java.io.IOException


sealed class CommonException(
    override val message: String?
) : IOException(message) {
    data class NetworkError(override val message: String = "네트워크 연결에 실패했습니다.") : CommonException(message)
    data class UnauthorizedError(override val message: String = "인증에 실패했습니다.") : CommonException(message)
    data class InvalidNameError(override val message: String = "유효하지 않은 이름입니다.") : CommonException(message)
    data class InvalidPhoneNumberError(override val message: String = "유효하지 않은 전화번호입니다.") : CommonException(message)
    data class InvalidPasswordError(override val message: String = "유효하지 않은 비밀번호입니다.") : CommonException(message)
    data class PasswordCheckError(override val message: String = "비밀번호가 일치하지 않습니다.") : CommonException(message)
    data class TimeOutError(override val message: String = "시간이 만료되었습니다.") : CommonException(message)
    data class UnknownError(override val message: String = "알 수 없는 에러가 발생했습니다.\n종료 후 다시 시도해주세요.") : CommonException(message)
}