package com.example.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun buildDatabase(builder: RoomDatabase.Builder<MomentDatabase>): MomentDatabase {
    return builder
        .setDriver(BundledSQLiteDriver()) // KMP 권장 드라이버
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}