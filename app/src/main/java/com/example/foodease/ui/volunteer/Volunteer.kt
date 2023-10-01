package com.example.foodease.ui.volunteer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volunteers")
data class Volunteer(
    @PrimaryKey
    var id : Int? = null,
    var name: String = "",
    var email: String = "",
    var contact:String = "",
    var birth : String = "",
    var gender : String = "",
    var address:String ="",
    var status : String = ""
)