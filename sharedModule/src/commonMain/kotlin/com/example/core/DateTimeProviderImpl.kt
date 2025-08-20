package com.example.core

import kotlinx.datetime.Clock as KxClock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTimeProviderImpl(
    private val timeZone: TimeZone = TimeZone.currentSystemDefault()
) : DateTimeProvider {

    override fun now(): Long =
        KxClock.System.now().toEpochMilliseconds()      // ← 완전수식

    override fun currentDateKey(): String =
        KxClock.System.now()
            .toLocalDateTime(timeZone)
            .date
            .toString()                                                // yyyy-MM-dd

    override fun dateKeyOf(epochMillis: Long): String =
        Instant.fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(timeZone)
            .date
            .toString()
}