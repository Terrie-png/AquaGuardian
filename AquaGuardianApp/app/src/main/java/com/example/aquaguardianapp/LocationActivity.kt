package com.example.aquaguardianapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aquaguardianapp.databinding.ActivityLocationBinding
import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
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

        // To obtain the SupportMapFragment and to get notified when the map is ready to be used.
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
                    val builder = LatLngBounds.Builder()
                    response.body()?.forEach { location ->
                        val latLng = LatLng(location.latitude, location.longitude)
                        mMap.addMarker(MarkerOptions().position(latLng).title("Possible Contaminated Area"))

                        // Adding a red circle around the location
                        val circleOptions = CircleOptions()
                            .center(latLng)
                            .radius(1000.0) // In meters
                            .strokeWidth(3f)
                            .strokeColor(R.color.red) // Border color of the circle
                            .fillColor(0x22FF0000) // Fill color of the circle, semi-transparent
                        mMap.addCircle(circleOptions)

                        builder.include(latLng)
                    }
                    val bounds = builder.build()

                    // Adjusting the padding for the camera update
                    val padding = 100 // offset from edges of the map in pixels
                    val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                    mMap.animateCamera(cu)
                }
            }

            override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
                // Handle failure
                Log.e("LocationActivity", "Error fetching locations", t)
                Toast.makeText(this@LocationActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}