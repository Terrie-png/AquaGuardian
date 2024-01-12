//package com.example.aquaguardianapp
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.example.aquaguardianapp.databinding.ActivityLoginBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // View binding setup
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Initialize Firebase Auth
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        // Set up the login button click listener
//        binding.btnLogin.setOnClickListener {
//            performLogin()
//        }
//       // Set up the register here text click listener
//                binding.tvRegisterHere.setOnClickListener {
//                    // Navigate to the RegisterActivity
//                    val intent = Intent(this, RegisterActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//    }
//    private fun performLogin() {
//        val username = binding.etLoginUsername.text.toString()
//        val password = binding.etLoginPassword.text.toString()
//
//        // Check for validity of email and password
//        if (username.isNotEmpty() && password.isNotEmpty()) {
//            firebaseAuth.signInWithEmailAndPassword(username, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign-in success, navigate to the next screen
//                        val intent = Intent(this, DashboardActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        task.exception?.message?.let {
//                            // Handle login failure
//                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//        } else {
//            // Handle case where either email or password is empty
//            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
package com.example.aquaguardianapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aquaguardianapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is already logged in
        if (firebaseAuth.currentUser != null) {
            // User is already logged in, greet them and navigate to Dashboard
            greetUser(firebaseAuth.currentUser?.email ?: "User")
            navigateToDashboard()
        }

        binding.btnLogin.setOnClickListener {
            performLogin()
        }

        binding.tvRegisterHere.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
    private fun greetUser(email: String) {
        Toast.makeText(this, "Welcome back, $email!", Toast.LENGTH_LONG).show()
    }
    private fun performLogin() {
        val email = binding.etLoginUsername.text.toString()
        val password = binding.etLoginPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.getIdToken(true)?.addOnCompleteListener { idTokenResult ->
                        if (idTokenResult.isSuccessful) {
                            val idToken = idTokenResult.result?.token
                            // Send token to your backend via HTTPS
                            sendTokenToServer(idToken)
                        } else {
                            Toast.makeText(this, "Failed to get ID token", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    task.exception?.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendTokenToServer(idToken: String?) {
        val retrofit = ServiceBuilder.buildService(APIInterface::class.java)
        retrofit.loginWithToken(idToken ?: "").enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    navigateToDashboard()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}
