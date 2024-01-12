package com.example.aquaguardianapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aquaguardianapp.databinding.ActivityMainBinding
import com.example.aquaguardianapp.databinding.ActivityWaterQualityBinding
import com.example.aquaguardianapp.ui.theme.AquaGuardianAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegisterLink.setOnClickListener{
            //Create an Intent to start the RegisterActivity
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener{
            //Create an Intent to start the LoginActivity
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
