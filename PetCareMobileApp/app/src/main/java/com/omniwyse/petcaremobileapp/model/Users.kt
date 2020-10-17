package com.omniwyse.petcaremobileapp.model

import java.io.Serializable

data class Users (
    val name: String,
    val email: String,
    val mobile: String,
    val password:String,
    val confirmPassword:String,
    val address: String
)


