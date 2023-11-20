package com.example.aquaguardianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var tvLoginLink: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //connecting the .xml layout file to kotlin file
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etAddress = findViewById(R.id.etAddress)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)
        tvLoginLink = findViewById(R.id.tvLoginLink)

        btnRegister.setOnClickListener {
            registerUser()
        }
        tvLogin.setOnClickListener {
            // Navigate to the login activity
            // val intent = Intent(this, LoginActivity::class.java)
            // startActivity(intent)
        }
    }

    private fun registerUser() {
        val username= etUsername.text.toString().trim()
        val password= etPassword.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhoneNumber.text.toString().trim()
        val address = etAddress.text.toString().trim()

        //validate
    }
}