package com.example.foodease.ui.request
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("request")
data class Request (
    @PrimaryKey
    val id: String = "",
    val email: String = "",
    val number: String = "",
    val address : String = "",
    val people : String = "",
    val dietary : String = ""
)
