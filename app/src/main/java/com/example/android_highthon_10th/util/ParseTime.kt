package com.example.android_highthon_10th.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun parseTime(timeString: String): Pair<Int, Int> {
    val regex = """(\d{2})시 (\d{2})분""".toRegex()

    val matchResult = regex.matchEntire(timeString)
    return if (matchResult != null) {
        val (hour, minute) = matchResult.destructured
        Pair(hour.toInt(), minute.toInt())
    } else {
        Pair(0, 0)
    }
}

fun formatDate(iso8601String: String): String {
    val zonedDateTime = ZonedDateTime.parse(iso8601String)

    val formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd")
    return zonedDateTime.format(formatter)
}