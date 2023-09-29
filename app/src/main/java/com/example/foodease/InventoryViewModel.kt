package com.example.foodease

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.foodease.database.InventoryDatabase
import com.example.foodease.database.InventoryRepository
import com.example.foodease.entities.Inventory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application): AndroidViewModel(application)  {
//    private val repository: InventoryRepository
//    var allInventory: LiveData<List<Inventory>>

    private val dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("inventory")
    private val _inventoryList: MutableLiveData<List<Inventory>> = MutableLiveData()
    val inventoryList: LiveData<List<Inventory>> = _inventoryList

    init {
//        val inventoryDao = InventoryDatabase.getDatabase(application).inventoryDao()
//        repository = InventoryRepository(inventoryDao)
//        allInventory = repository.getAllInventoryItems
        loadDataFromFirebase()
    }


    private fun loadDataFromFirebase() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val inventoryData = mutableListOf<Inventory>()
                for (data in snapshot.children) {
                    val inventory = data.getValue(Inventory::class.java)
                    inventory?.id = data.key
                    inventory?.let { inventoryData.add(it) }
                }
                _inventoryList.value = inventoryData
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors here
            }
        })
    }

//    fun insert(inventory: Inventory) {
//        viewModelScope.launch{
//            repository.insert(inventory)
//        }
//    }
//
//    fun update(inventory: Inventory) {
//        viewModelScope.launch{
//            repository.update(inventory)
//        }
//    }
//
//    fun delete(inventory: Inventory) {
//        viewModelScope.launch{
//            repository.delete(inventory)
//        }
//
//
//    }

}