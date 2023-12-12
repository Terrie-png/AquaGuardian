package com.example.aquaguardianapp


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
 main
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


@Composable
fun ShapeGreen() {
    Log.d("ShapeGreen", "ShapeGreen called")
 main
    Box(

        modifier = Modifier
            .background(color = Color.Green)
            .size(100.dp)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
    }
}
@Composable
fun ShapeRed() {
    Log.d("ShapeRed", "ShapeRed called")

    Box(

        modifier = Modifier
            .background(color = Color.Red)
            .size(100.dp)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
    }
    }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun activeDevice(
    backButton: () -> Unit,
    moreDeviceInfo: () -> Unit,

    modifier: Modifier = Modifier,
) {
    Log.d("Active Device", "Active Device page called")
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                        IconButton(onClick = { backButton() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                },
                title = {
                    Text("Active Devices",
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
                text = "Device 1",
                modifier = Modifier.padding(top = 60.dp),
                fontSize = 50.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = { moreDeviceInfo() }) {
                Log.d("Active Device", "Device 1 button clicked")
                ShapeRed()
            }
            Text(
                text =" WARNING! Click on the square for more info ",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 15.sp,
                color = (Color(0xFFCE0404)),
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Device 2",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                )
            IconButton(onClick = { }) {
                Log.d("Active Device", "Device 2 button clicked")
                ShapeGreen()
            }
            Text(
                text =" Water quality is good ",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 15.sp,
                color = (Color(0xFF146302)),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Device 3",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            IconButton(onClick = { }) {
                Log.d("Active Device", "Device 3 button clicked")
                ShapeGreen()
            }
            Text(
                text =" Water quality is good ",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 15.sp,
                color = (Color(0xFF146302)),
                fontWeight = FontWeight.Bold,
            )
        }
    }
  )
}


