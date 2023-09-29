package com.example.foodease.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("inventory")
data class Inventory (
    @PrimaryKey(autoGenerate = true)
    var id: String? = null,
    var name:String? = null,
    var description: String? = null,
    var date: String? = null,
    var quantity: String? = null
)