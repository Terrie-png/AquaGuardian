package com.example.aquaguardianapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Message(
    val message: String,
    val device: String,
    val timestamp: String,
    val date: String,
    val longitude: String,
    val latitude: String
)



@Composable
fun MesageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageRow(message)

        }
    }
}

@Composable
private fun MessageRow(message: Message) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = message.device)
        Text(text = message.message, color = Color.Red)
        Text(text = message.timestamp, color = Color.Gray)
        Text(text = message.date, color = Color.Blue)
        Text(text = message.longitude, color = Color.Green)
        Text(text = message.latitude)
        Spacer(modifier = Modifier.height(30.dp))


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun history(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("History", "History page called")
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { backButton() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        "History",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive)
                }
            )
        },
        content = {
            Column(
                modifier = modifier.fillMaxSize().padding(top = 70.dp).background(Color(0xFF00C2FF)),
            ) {
                MesageList(
                    messages = listOf(
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:43",
                            "23/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "08:43",
                            "23/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Dirty ",
                            "Device 3",
                            "12:46",
                            "23/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "12:06",
                            "24/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "18:55",
                            "24/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "22:35",
                            "24/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),
                        Message(
                            " Clean ",
                            "Device 1",
                            "03:56",
                            "25/11/2023",
                            "-90.33123",
                            "38.7545"
                        ),
                        Message(
                            " Clean ",
                            "Device 2",
                            "03:12",
                            "25/11/2023",
                            "-91.22123",
                            "23.1634"
                        ),
                        Message(
                            " Clean ",
                            "Device 3",
                            "04:44",
                            "25/11/2023",
                            "-92.78643",
                            "41.1145"
                        ),

                        )
                )
            }
        }
    )
}
