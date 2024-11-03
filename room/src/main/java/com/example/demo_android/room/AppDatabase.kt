package com.example.demo_android.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        TimeStamp::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timeStampDao(): TimeStampDao
}
