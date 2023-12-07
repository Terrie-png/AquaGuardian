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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import retrofit2.http.GET

data class Message(
    val message: String,
    val device: String,
    val timestamp: String,
    val date: String,
    val longitude: String,
//    val latitude: String
)

interface ApiService {
    @GET("messages")
    suspend fun getMessage(): List<Message>
}

object ApiClient{
    private const val BASE_URL = "https://demo2971444.mockable.io/"
private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}


@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        itemsIndexed(messages) {index, message ->
            MessageRow(message,index)

        }
    }
}

@Composable
private fun MessageRow(message: Message,index: Int) {
    val backgroundColor = if (index % 2 == 0) Color(0xFF039AC9) else Color(0xFF00C2FF)
    val qualityColor = if (message.message == " Clean ") Color(0xFF00FF00) else Color(0xFFFF0000)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = message.device)
        Text(text = message.message, color = qualityColor)
        Text(text = message.timestamp, color = Color.White)
        Text(text = message.date, color = Color.LightGray)
        Text(text = message.longitude, color = Color.White)
//        Text(text = message.latitude, overflow = TextOverflow.Clip)

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun history(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MessageViewModel = viewModel(),

)

{ LaunchedEffect(viewModel) {
        viewModel.loadMessages()
    }

    val messages by viewModel.messages.observeAsState(listOf())


    Log.d("History", "History page called")



    if (messages.isEmpty()) {
        Log.d("History", "Messages is empty")
    } else {
        Log.d("History", "Messages is not empty")
    }
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
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 70.dp)
                    .background(Color(0xFF00C2FF)),
            ) {
                MessageList(messages = listOf(
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        "23/11/2023",
                        "-90.33123",
//                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        "23/11/2023",
                        "-91.22123",
//                            "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        "23/11/2023",
                        "-92.78643",
//                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        "24/11/2023",
                        "-90.33123",
//                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        "24/11/2023",
                        "-91.22123",
//                            "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        "24/11/2023",
                        "-92.78643",
//                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        "25/11/2023",
                        "-90.33123",
//                              "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        "25/11/2023",
                        "-91.22123",
                        //                             "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        "25/11/2023",
                        "-92.78643",
                        //                             "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        "23/11/2023",
                        "-90.33123",
                        //                             "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        "23/11/2023",
                        "-91.22123",
                        //                            "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        "23/11/2023",
                        "-92.78643",
                        //                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        "24/11/2023",
                        "-90.33123",
                        //                            "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        "24/11/2023",
                        "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        "24/11/2023",
                        "-92.78643",
                        //                            "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        "25/11/2023",
                        "-90.33123",
                        //                          "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        "25/11/2023",
                        "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        "25/11/2023",
                        "-92.78643",
                        //                          "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        "23/11/2023",
                        "-90.33123",
                        //                           "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        "23/11/2023",
                        "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        "23/11/2023",
                        "-92.78643",
                        //                           "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        "24/11/2023",
                        "-90.33123",
                        //                             "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        "24/11/2023",
                        "-91.22123",
                        //                           "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        "24/11/2023",
                        "-92.78643",
                        //                          "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "03:56",
                        "25/11/2023",
                        "-90.33123",
                        //                           "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "03:12",
                        "25/11/2023",
                        "-91.22123",
                        //       "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "04:44",
                        "25/11/2023",
                        "-92.78643",
                        //        "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:43",
                        "23/11/2023",
                        "-90.33123",
                        //        "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "08:43",
                        "23/11/2023",
                        "-91.22123",
                        //        "23.1634"
                    ),
                    Message(
                        " Dirty ",
                        "Device 3",
                        "12:46",
                        "23/11/2023",
                        "-92.78643",
                        //         "41.1145"
                    ),
                    Message(
                        " Clean ",
                        "Device 1",
                        "12:06",
                        "24/11/2023",
                        "-90.33123",
                        //        "38.7545"
                    ),
                    Message(
                        " Clean ",
                        "Device 2",
                        "18:55",
                        "24/11/2023",
                        "-91.22123",
                        //       "23.1634"
                    ),
                    Message(
                        " Clean ",
                        "Device 3",
                        "22:35",
                        "24/11/2023",
                        "-92.78643",
                        //       "41.1145"
                    ),
                )
                )
            }
        }
    )
}
