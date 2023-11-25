package com.example.aquaguardianapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/register")
    fun registerUser(@Body user: User): Call<RegistrationResponse>
}