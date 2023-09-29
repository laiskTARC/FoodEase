package com.example.foodease.database.event

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodease.ui.event.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM events ORDER BY name ASC")
    fun getAllEvent(): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Update
    suspend fun update(event: Event)
}
