package com.example.aquaguardianapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aquaguardianapp.databinding.ActivityLocationBinding
import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityLocationBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Fetch locations using Retrofit and add markers
        fetchLocationsAndMark()
    }
    private fun fetchLocationsAndMark() {

        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.getLocations().enqueue(
            object : Callback<List<LocationResponse>> {
            override fun onResponse(call: Call<List<LocationResponse>>, response: Response<List<LocationResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.forEach { location ->
                        mMap.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)).title("DKIT"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location.latitude, location.longitude)))
                    }
                }
            }

            override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}