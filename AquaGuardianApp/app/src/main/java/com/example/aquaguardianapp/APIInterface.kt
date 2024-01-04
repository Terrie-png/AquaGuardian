package com.example.aquaguardianapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIInterface {
    @POST("register")
    fun registerUser(@Header("Authorization") idToken: String?, @Body user: User): Call<RegistrationResponse>

    @GET("waterQuality")
    fun checkWaterQuality(): Call<WaterQualityResponse>

    @GET("locations")
    fun getLocations():Call<List<LocationResponse>>

    @GET("history")
    fun getHistory(): Call<HistoryResponse>

    // API post request to clear the history from the backend.
    //@POST("clearHistory")
    //    fun clearHistory(): Call<Void>
}