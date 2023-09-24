package com.example.foodease.entities
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("events")
data class Event(
    @PrimaryKey(true)
    val id: Int? = null,
    val name: String,
    val startingDateTime: String,
    val endingDateTime: String,
    val description: String,
    val volunteerRequired: Int,
    val address: String
)