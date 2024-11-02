package com.example.demo_android.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeStampDao {
    @Insert
    fun insertAll(vararg timeStamp: TimeStamp)

    @Query("DELETE FROM time_stamp WHERE id IN (:id)")
    fun deleteAll(vararg id: Long)

    @Query("SELECT * FROM time_stamp")
    fun getAll(): List<TimeStamp>

    @Query("SELECT * FROM time_stamp")
    fun getAllFlow(): Flow<List<TimeStamp>>
}
