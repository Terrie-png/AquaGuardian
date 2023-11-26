package com.example.aquaguardianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
import com.example.aquaguardianapp.databinding.ActivityWaterQualityBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaterQualityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWaterQualityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityWaterQualityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheckWaterQuality.setOnClickListener{
            checkWaterQuality()
        }
    }

    private fun checkWaterQuality() {
//        // Logic to check water quality
//        // This could involve fetching data from a sensor or an API
//        // For now, let's just simulate the check with a hardcoded value
//        val isDrinkable = false // Replace with actual water quality check logic

        val serviceBuilder = ServiceBuilder.buildService(APIInterface::class.java)
        val call = serviceBuilder.checkWaterQuality()

        call.enqueue(object : Callback<WaterQualityResponse> {
            override fun onResponse(call: Call<WaterQualityResponse>, response: Response<WaterQualityResponse>) {
                if (response.isSuccessful) {
                    val isDrinkable= response.body()?.quality
                    if (isDrinkable != null) {
                        updateWaterQuality(isDrinkable)
                    }
                } else {
                    Toast.makeText(this@WaterQualityActivity,"No water quality data received", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<WaterQualityResponse>, t: Throwable) {
                // Handle network failure or other errors
                Toast.makeText(this@WaterQualityActivity,"Failed to register. Please check your network connection and try again.", Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun updateWaterQuality(isDrinkable: Boolean) {
        if (isDrinkable) {
            // Show green happy emoji and corresponding message with green text color
            binding.imgEmoji.setImageResource(R.drawable.happyface)
            binding.txtWaterQualityMessage.text = getString(R.string.water_quality_good)
            binding.txtWaterQualityMessage.setTextColor(ContextCompat.getColor(this, R.color.green))
        } else {
            // Show red sad emoji and corresponding message with red text color
            binding.imgEmoji.setImageResource(R.drawable.sadface)
            binding.txtWaterQualityMessage.text = getString(R.string.water_quality_bad)
            binding.txtWaterQualityMessage.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}