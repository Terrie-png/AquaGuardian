package com.example.aquaguardianapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
          performRegistration()
        }
    }
    private fun performRegistration() {
        val name = binding.etName.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val address = binding.etAddress.text.toString()

        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)

        // Check if any field is empty
        if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty()) {
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
                if (it.isSuccessful) {
                    val firebaseUser = it.result?.user
                    val userUId = firebaseUser?.uid
                    val newUser = User(username, name, email, phoneNumber, address, userUId)

                    retrofit.registerUser(newUser).enqueue(
                        object : Callback<RegistrationResponse> {
                            override fun onResponse(
                                call: Call<RegistrationResponse>,
                                response: Response<RegistrationResponse>
                            ) {
                                Log.d("TAG", "${response.body()?.success.toString()}")
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registration successful.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Only navigate to DashboardActivity if the registration is successful
                                if (response.isSuccessful) {
                                    //Create an Intent to start the DashboardActivity
                                    val intent = Intent(
                                        this@RegisterActivity,
                                        DashboardActivity::class.java
                                    )
                                    startActivity(intent)
                                    finish()
                                }
                            }

                            override fun onFailure(
                                call: Call<RegistrationResponse>,
                                t: Throwable
                            ) {
                                AlertDialog.Builder(this@RegisterActivity).apply {
                                    setTitle("Registration Failed")
                                    setMessage("Failed to register. Please check your network connection and try again.")
                                    setPositiveButton("Retry") { dialog, which ->
                                        // Call the performRegistration method to retry
                                        performRegistration()
                                    }
                                    setNegativeButton("Cancel") { dialog, which ->
                                        dialog.dismiss()
                                    }
                                    show()
                                }
                            }
                        }
                    )

                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}