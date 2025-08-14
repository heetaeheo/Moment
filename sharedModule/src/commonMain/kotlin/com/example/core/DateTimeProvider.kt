package com.example.core

interface DateTimeProvider {
    fun now(): Long
    fun currentDateKey(): String
    fun dateKeyOf(epochMillis: Long): String
}