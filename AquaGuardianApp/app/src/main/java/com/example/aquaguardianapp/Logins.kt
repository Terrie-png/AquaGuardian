package com.example.aquaguardianapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Logins(
    loginSuccess: () -> Unit,
    modifier: Modifier = Modifier,

    ) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aqua Guardian",
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Cursive
        )
        Image(
            painter = painterResource(id = R.drawable.aglogo),
            contentDescription = "Aqua Guardian Logo",
            modifier = Modifier
                .padding(top = 40.dp)
                .size(200.dp)

        )

        LoginTextBox(username = username) { username = it }
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(password = password) { password = it }

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
            )
        }

        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    loginSuccess()
                    Log.d("MainActivity", "Login Succesfull")

                } else if (username.isBlank() && password.isNotBlank()) {
                    errorMessage = "Please enter the username"
                    Log.d("MainActivity", "Invalid Login , username missing")

                } else if (password.isBlank() && username.isNotBlank()) {
                    errorMessage = "Please enter the password"
                    Log.d("MainActivity", "Invalid Login , password missing")

                } else {
                    errorMessage = "Please enter username and password"
                    Log.d("MainActivity", "Missing Credentials")

                }
            }
        ) {
            Text(
                text = "Login",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )

        }
    }
}