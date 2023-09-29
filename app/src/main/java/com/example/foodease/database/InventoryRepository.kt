package com.example.foodease.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.foodease.dao.InventoryDao
import com.example.foodease.entities.Inventory

class InventoryRepository(private val inventoryDao: InventoryDao) {
    val getAllInventoryItems: LiveData<List<Inventory>> = inventoryDao.getAllInventoryItems()

    suspend fun insert(item: Inventory) {
        inventoryDao.insert(item)
    }

    suspend fun update(item: Inventory) {
        inventoryDao.update(item)
    }

    suspend fun delete(item: Inventory) {
        inventoryDao.delete(item)
    }
}