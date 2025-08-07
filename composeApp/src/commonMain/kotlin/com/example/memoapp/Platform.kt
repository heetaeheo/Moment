package com.example.memoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform