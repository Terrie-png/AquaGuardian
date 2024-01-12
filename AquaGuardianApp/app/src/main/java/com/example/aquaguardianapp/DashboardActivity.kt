package com.example.aquaguardianapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aquaguardianapp.databinding.ActivityDashboardBinding
import com.example.aquaguardianapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.StringBuilder

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

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
        // Click listener for the Logout button
        binding.btnLogout.setOnClickListener {
            // Sign out from Firebase
            firebaseAuth.signOut()

            // Show a toast message
            Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show()

            // Redirect to LoginActivity
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so the user won't be able to go back to the dashboard after logging out
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}