package com.example.foodease.ui.event
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName= "events")
data class Event(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val venueAddress : String = "",
    val startingDate : String = "",
    val endingDate : String = "",
    val volunteerRequired : Int = 0,
    val imageUrl : String = ""
)