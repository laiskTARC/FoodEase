package com.example.foodease.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volunteers")
data class Volunteer(

    var name: String,
    var email: String,
    var contact:String,
    var address:String,
    @PrimaryKey(true) var id : Int
) {

}