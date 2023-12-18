package com.example.aquaguardianapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aquaguardianapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View binding setup
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Set up the login button click listener
        binding.btnLogin.setOnClickListener {
            performLogin()
        }
       // Set up the register here text click listener
                binding.tvRegisterHere.setOnClickListener {
                    // Navigate to the RegisterActivity
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    finish()
                }
    }
    private fun performLogin() {
        val username = binding.etLoginUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()

        // Check for validity of email and password
        if (username.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign-in success, navigate to the next screen
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        task.exception?.message?.let {
                            // Handle login failure
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        } else {
            // Handle case where either email or password is empty
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
        }
    }
}