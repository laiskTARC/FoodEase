package com.example.foodease.ui.request
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("request")
data class Request (
    @PrimaryKey
    var id: String = "",
    var email: String = "",
    var number: String = "",
    var address : String = "",
    var people : String = "",
    var dietary : String = ""
)
