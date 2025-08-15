package com.example.core

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateTimeProviderAndroid(
    private val zoneId: ZoneId = ZoneId.systemDefault()
): DateTimeProvider {

    private val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    override fun now(): Long = System.currentTimeMillis()
    override fun currentDateKey(): String = LocalDate.now(zoneId).format(fmt)
    override fun dateKeyOf(epochMillis: Long): String =
        Instant.ofEpochMilli(epochMillis).atZone(zoneId).toLocalDate().format(fmt)

}