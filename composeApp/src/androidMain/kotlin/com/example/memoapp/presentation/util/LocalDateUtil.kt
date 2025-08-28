package com.example.memoapp.presentation.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


fun String.toLocalDateOrNull(): LocalDate? {
    return try {
        LocalDate.parse(this, dateFormatter)
    } catch (e: DateTimeParseException) {
        null
    }
}

fun LocalDate.toDateKey(): String = format(dateFormatter)