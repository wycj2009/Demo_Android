package com.example.demo_android.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeStamp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timeStampDao(): TimeStampDao
}
