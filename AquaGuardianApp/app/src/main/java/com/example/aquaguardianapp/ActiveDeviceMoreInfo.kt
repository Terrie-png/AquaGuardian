package com.example.aquaguardianapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun activeDeviceMoreInfo(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("Device 1 Info", "Device 1 Info screen called")
    var text by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { backButton() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text("Device 1 Info",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive)
                }


            )
        },
        content = {
            Column(
                modifier = modifier.fillMaxSize().background(Color(0xFF00C2FF)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "WARNING: Device 1",
                    modifier = Modifier.padding(top = 40.dp),
                    fontSize = 30.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "PH Level is too high!",
                    modifier = Modifier.padding(top = 20.dp),
                    fontSize = 30.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                )
                ShapeRed()
                Text(
                    text = "PH Level: 5.9",
                    modifier = Modifier.padding(top = 40.dp),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "TDS Level: 0.5",
                    modifier = Modifier.padding(top = 40.dp),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Turbidity Level: 0.5",
                    modifier = Modifier.padding(top = 40.dp),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}

