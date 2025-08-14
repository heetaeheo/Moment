package com.example.sharedmodule.core

import com.example.core.DateTimeProvider

//class DateTimeProviderIos(
//    private val tz: TimeZone = TimeZone.currentSystemDefault()
//): DateTimeProvider {
//    override fun now(): Long = Clock.System.now().toEpochMilliseconds()
//    override fun currentDateKey(): String = dateKeyOf(now())
//    override fun dateKeyOf(epochMillis: Long): String {
//        val instant = Instant.fromEpochMilliseconds(epochMillis)
//        val localDate = instant.toLocalDateTime(tz).date
//        val y = localDate.year.toString().padStart(4,'0')
//        val m = localDate.monthNumber.toString().padStart(2,'0')
//        val d = localDate.dayOfMonth.toString().padStart(2,'0')
//        return "$y-$m-$d"
//    }
//}