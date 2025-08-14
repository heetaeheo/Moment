package com.example.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MomentDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("moment_database.db")
    return Room.databaseBuilder(context = appContext, name = dbFile.absolutePath)
}


