package com.example.foodease

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volunteers")
data class Volunteer(
    @PrimaryKey(true) var id : Int,
    var name: String,
    var email: String,
    var contact:String,
    var address:String

) {

}