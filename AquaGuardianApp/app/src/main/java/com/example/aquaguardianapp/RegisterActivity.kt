package com.example.aquaguardianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Patterns
import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

//    private lateinit var etUsername: EditText
//    private lateinit var etPassword: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etPhoneNumber: EditText
//    private lateinit var etAddress: EditText
//    private lateinit var btnRegister: Button

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        //connecting the .xml layout file to kotlin file
//        setContentView(R.layout.activity_register)
//
//        etUsername = findViewById(R.id.etUsername)
//        etPassword = findViewById(R.id.etPassword)
//        etEmail = findViewById(R.id.etEmail)
//        etPhoneNumber = findViewById(R.id.etPhoneNumber)
//        etAddress = findViewById(R.id.etAddress)
//        btnRegister = findViewById(R.id.btnRegister)
//
//        btnRegister.setOnClickListener {
//            registerUser()
//        }
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val address = binding.etAddress.text.toString()

            // Check if any field is empty
            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty()) {
                // Validate the email format
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT)
                        .show()
                }
                // Validate the password length
                if (password.length < 6) {
                    Toast.makeText(
                        this,
                        "Password must be at least 6 characters long.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Validate the phone number
                if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
                    Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT)
                        .show()
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            }

        }


    }
}
//    private fun registerUser() {
//        val username= etUsername.text.toString().trim()
//        val password= etPassword.text.toString().trim()
//        val email = etEmail.text.toString().trim()
//        val phone = etPhoneNumber.text.toString().trim()
//        val address = etAddress.text.toString().trim()
//
//        //validate the inputs
//        if (username.isEmpty()) {
//            etUsername.error = "Username cannot be empty"
//            return
//        }
//
//        if (username.length < 3) {
//            etUsername.error = "Username must be at least 3 characters long"
//            return
//        }
//
//        if (password.isEmpty()) {
//            etPassword.error = "Password cannot be empty"
//            return
//        }
//
//        if (password.length < 8) {
//            etPassword.error = "Password must be at least 8 characters long"
//            return
//        }
//
//        if (email.isEmpty()) {
//            etEmail.error = "Email cannot be empty"
//            return
//        }
//
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            etEmail.error = "Invalid email address"
//            return
//        }
//
//        if (phone.isEmpty()) {
//            etPhoneNumber.error = "Phone number cannot be empty"
//            return
//        }
//
//        if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
//            etPhoneNumber.error = "Invalid phone number"
//            return
//        }
//
//        if (address.isEmpty()) {
//            etAddress.error = "Address cannot be empty"
//            return
//        }
//        //Success message
//        Toast.makeText(this@RegisterActivity, "Registration successful!", Toast.LENGTH_LONG).show()
//
//    }
//private fun registerUser() {
//    // Retrieve the input from the EditTexts
//    val username = findViewById<EditText>(R.id.etUsername).text.toString().trim()
//    val password = findViewById<EditText>(R.id.etPassword).text.toString().trim()
//    val email = findViewById<EditText>(R.id.etEmail).text.toString().trim()
//    val phoneNumber = findViewById<EditText>(R.id.etPhoneNumber).text.toString().trim()
//    val address = findViewById<EditText>(R.id.etAddress).text.toString().trim()
//
//    // Check if any field is empty
//    if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
//        Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
//        return
//    }
//
//    // Validate the email format
//    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//        Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
//        return
//    }
//
//    // Validate the password length
//    if (password.length < 6) {
//        Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show()
//        return
//    }
//
//    // Validate the phone number
//    if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
//        Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show()
//        return
//    }
//
//    // If all validations pass, proceed with the registration process
//    // Since we do not have an actual backend, we'll just show a success message
//    Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show()
//
//    // TODO: Implement actual registration logic here
//
//    // For now, we'll just finish the activity to simulate going back to the login screen
//    finish()
//}
//
//}