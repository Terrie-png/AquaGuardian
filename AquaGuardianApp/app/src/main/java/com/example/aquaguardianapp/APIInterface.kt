package com.example.aquaguardianapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("register")
    fun registerUser(@Body user: User): Call<RegistrationResponse>

    @GET("waterQuality")
    fun checkWaterQuality(): Call<WaterQualityResponse>

    @GET("locations")
    fun getLocations():Call<List<LocationResponse>>
}