package com.example.foodease.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodease.entities.Inventory
import com.google.firebase.database.DatabaseReference
import io.reactivex.Single

interface InventoryDao {

    @Query("SELECT * FROM inventory")
    fun getAllInventoryItems(): LiveData<List<Inventory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Inventory)

    @Update
    suspend fun update(item: Inventory)

    @Delete
    suspend fun delete(item: Inventory)



}