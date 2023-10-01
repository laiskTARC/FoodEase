package com.example.foodease.database.inventory

import androidx.lifecycle.LiveData
import com.example.foodease.database.inventory.InventoryDao
import com.example.foodease.ui.inventory.Inventory

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