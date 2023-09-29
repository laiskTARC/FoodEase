package com.example.foodease.ui.donation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donations")
data class Donation(
    @PrimaryKey
    var id : Int= 0,
    var name: String = "",
    var instruction : String = "",
    var quantity : Int = 0,
    var expiryDate : Int = 0
)