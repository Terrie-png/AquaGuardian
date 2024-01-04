//package com.example.aquaguardianapp
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import android.util.Patterns
//import androidx.appcompat.app.AlertDialog
//import com.example.aquaguardianapp.databinding.ActivityRegisterBinding
//import com.google.firebase.auth.FirebaseAuth
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class RegisterActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityRegisterBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityRegisterBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        binding.btnRegister.setOnClickListener {
//          performRegistration()
//        }
//        binding.tvLoginLink.setOnClickListener {
//            val intent = Intent(
//                this@RegisterActivity,
//                LoginActivity::class.java
//            )
//            startActivity(intent)
//            finish()
//        }
//    }
//    private fun performRegistration() {
//        val name = binding.etName.text.toString()
//        val username = binding.etUsername.text.toString()
//        val password = binding.etPassword.text.toString()
//        val email = binding.etEmail.text.toString()
//        val phoneNumber = binding.etPhoneNumber.text.toString()
//        val address = binding.etAddress.text.toString()
//
//        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
//
//        // Check if any field is empty
//        if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && address.isNotEmpty()) {
//            // Validate the email format
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            // Validate the password length
//            if (password.length < 6) {
//                Toast.makeText(
//                    this,
//                    "Password must be at least 6 characters long.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            // Validate the phone number
//            if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
//                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    val firebaseUser = it.result?.user
//                    val userUId = firebaseUser?.uid
//                    val newUser = User(username, name, email, phoneNumber, address, userUId)
//                    retrofit.registerUser(newUser).enqueue(
//                        object : Callback<RegistrationResponse> { override fun onResponse(
//                            call: Call<RegistrationResponse>,
//                            response: Response<RegistrationResponse>
//                            ) {
//                                Log.d("TAG", "${response.body()?.success.toString()}")
//                            if (response.isSuccessful) {
//                                val isRegistered= response.body()?.success
//                                if (isRegistered != null) {
//                                    Toast.makeText(
//                                        this@RegisterActivity,
//                                        "Registration successful.",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                    // Only navigate to DashboardActivity if the registration is successful
//                                    if (response.isSuccessful) {
//                                        //Create an Intent to start the DashboardActivity
//                                        val intent = Intent(
//                                            this@RegisterActivity,
//                                            DashboardActivity::class.java
//                                        )
//                                        startActivity(intent)
//                                        finish()
//                                    }
//                                }
//                            } else {
//                                Toast.makeText(this@RegisterActivity,"No user data received", Toast.LENGTH_SHORT).show()
//                            }
//                            }
//                            override fun onFailure(
//                                call: Call<RegistrationResponse>,
//                                t: Throwable
//                            ) {
//                                AlertDialog.Builder(this@RegisterActivity).apply {
//                                    setTitle("Registration Failed")
//                                    setMessage("Failed to register. Please check your network connection and try again.")
//                                    setPositiveButton("Retry") { dialog, which ->
//                                        // Call the performRegistration method to retry
//                                        performRegistration()
//                                    }
//                                    setNegativeButton("Cancel") { dialog, which ->
//                                        dialog.dismiss()
//                                    }
//                                    show()
//                                }
//                            }
//                        }
//                    )
//
//                } else {
//                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        } else {
//            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
//        }
//    }
//}

package com.example.aquaguardianapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        binding.tvLoginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun performRegistration() {
        val name = binding.etName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()

        if (validateInput(name, username, email, password, phoneNumber, address)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.user?.getIdToken(true)?.addOnCompleteListener { idTokenResult ->
                        if (idTokenResult.isSuccessful) {
                            val idToken = idTokenResult.result?.token
                            val newUser = User(username, name, email, phoneNumber, address, task.result?.user?.uid)
                            sendRegistrationDataToServer(idToken, newUser)
                        } else {
                            Toast.makeText(this, "Failed to get ID token", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInput(name: String, username: String, email: String, password: String, phoneNumber: String, address: String): Boolean {
        // Check if any field is empty
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate the email format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate the password length
        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate the phone number
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun sendRegistrationDataToServer(idToken: String?, newUser: User) {
        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.registerUser(idToken ?: "", newUser).enqueue(
            object : Callback<RegistrationResponse> {
                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@RegisterActivity, "Registration successful.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, DashboardActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Failed to register: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
