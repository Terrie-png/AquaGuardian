package com.example.aquaapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.aquaapplication.ui.theme.AquaApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaApplicationTheme {
                // A surface container using the 'background' color from the theme
                MainPage()
            }
        }
    }
}

@Preview
@Composable
fun MainPage(){
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(){
    CenterAlignedTopAppBar(title = { Text(stringResource(R.string.app_name))},modifier = Modifier)
}

//
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//    Image(painter = painterResource(R.drawable.logo), contentDescription = "background", contentScale = ContentScale.FillBounds, modifier = Modifier)
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AquaApplicationTheme {
//        Greeting("Android")
//    }
//}