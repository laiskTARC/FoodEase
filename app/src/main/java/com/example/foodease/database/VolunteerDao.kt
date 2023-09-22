package com.example.foodease.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodease.Volunteer

@Dao
interface ContactDao {
    @Query("SELECT * FROM volunteers ORDER BY name ASC")
    fun getAllContact(): LiveData<List<Volunteer>>

    @Query("DELETE FROM volunteers")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(volunteer: Volunteer)

    @Delete
    suspend fun delete(volunteer: Volunteer)

    @Update
    suspend fun update(volunteer: Volunteer)
}