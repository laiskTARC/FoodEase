package com.example.foodease.entities
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text


@Entity("events")
data class Event (
    @PrimaryKey(true)
    val id : Int? = null,
    val name : String,
    val startingDateTime : String,
    val endingDateTime : String,
    val description : Text,
    val volunteerRequired : Int,
    val address : String
)