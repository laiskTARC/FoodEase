package com.example.foodease.ui.inventory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("inventory")
data class Inventory (
    @PrimaryKey
    var id: String = "",
    var name:String? = null,
    var description: String? = null,
    var date: String? = null,
    var quantity: String? = null
)