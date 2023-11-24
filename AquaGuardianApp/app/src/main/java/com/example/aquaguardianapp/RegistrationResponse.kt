package com.example.aquaguardianapp

data class RegistrationResponse(
    val success: Boolean,
    val userId: Int? = null,
    val name: String,
    val userUID: String)
