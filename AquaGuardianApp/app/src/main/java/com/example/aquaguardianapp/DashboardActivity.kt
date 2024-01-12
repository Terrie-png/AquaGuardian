package com.example.aquaguardianapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aquaguardianapp.databinding.ActivityDashboardBinding
import com.example.aquaguardianapp.databinding.ActivityMainBinding
import java.lang.StringBuilder

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btActiveDevices.setOnClickListener{
            //Create an Intent to start the WaterQuality page
            val intent = Intent(this,WaterQualityActivity::class.java)
            startActivity(intent)
        }
        binding.btLocations.setOnClickListener{
            //Create an Intent to start the Locations page
            val intent = Intent(this,LocationActivity::class.java)
            startActivity(intent)
        }
        binding.btHistory.setOnClickListener{
            //Create an Intent to start the History page
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}