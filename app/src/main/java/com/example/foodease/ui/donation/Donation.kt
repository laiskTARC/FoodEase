package com.example.foodease.ui.donation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donations")
data class Donation(
    @PrimaryKey
    var id : String = "",
    var name: String? = "",
    var description : String? = "",
    var instruction : String? = "",
    var quantity : Int = 0,
    var expiryDate : String = ""
)