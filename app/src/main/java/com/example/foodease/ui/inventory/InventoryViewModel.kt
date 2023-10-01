package com.example.foodease.ui.inventory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodease.database.inventory.InventoryDatabase
import com.example.foodease.database.inventory.InventoryRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application): AndroidViewModel(application)  {
    private val repository: InventoryRepository
    var allInventory: LiveData<List<Inventory>>

    private val _inventoryList: MutableLiveData<List<Inventory>> = MutableLiveData()
    val inventoryList: LiveData<List<Inventory>> = _inventoryList

    init {
        val inventoryDao = InventoryDatabase.getDatabase(application).inventoryDao()
        repository = InventoryRepository(inventoryDao)
        allInventory = repository.getAllInventoryItems
    }

    fun insert(inventory: Inventory) {
        viewModelScope.launch{
            repository.insert(inventory)
        }
    }

    fun update(inventory: Inventory) {
        viewModelScope.launch{
            repository.update(inventory)
        }
    }

    fun delete(inventory: Inventory) {
        viewModelScope.launch{
            repository.delete(inventory)
        }


    }

}