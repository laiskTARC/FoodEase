package com.example.foodease.ui.user

data class User (
    var id : String = "",
    var name : String = "",
    var email : String = "",
    var age : Int? = null,
    var address : String? = null,
    var contact : String? = null
)