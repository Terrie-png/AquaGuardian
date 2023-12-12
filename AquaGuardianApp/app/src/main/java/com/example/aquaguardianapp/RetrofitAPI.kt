

package com.example.aquaguardianapp

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    // as we are making get request so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @GET("T7R2")
    fun
    // as we are calling data from array so we are calling
    // it with array list and naming that method as getAllCourses();
            getLanguages(): Call<ArrayList<ListModal>>

}