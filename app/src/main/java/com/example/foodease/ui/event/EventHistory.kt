package com.example.foodease.ui.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "event_history")
data class EventHistory(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val created_date: String = ""
)