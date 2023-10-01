package com.example.foodease.database.request

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodease.ui.request.Request

@Dao
interface RequestDao {

    @Query("SELECT * FROM request ORDER BY email ASC")
    fun getAllRequest(): LiveData<List<Request>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(request: Request)

    @Delete
    suspend fun delete(request: Request)

    @Update
    suspend fun update(request: Request)

}