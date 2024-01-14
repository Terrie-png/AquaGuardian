package com.example.aquaguardianapp

import android.os.Message
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalMapOf
import kotlinx.coroutines.delay

@Composable
fun ShapeGreen() {

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

    Box(

        modifier = Modifier
            .background(color = Color.Red)
            .size(100.dp)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
    }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun activeDevice(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("Active Device", "Active Device page called")

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = {
            Text(
                text = "Active Device",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )


        },
            navigationIcon = {
                IconButton(onClick = { backButton() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            })
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Device 1",
            modifier = Modifier.padding(top = 100.dp),
            fontSize = 50.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
        ShapeRed()
        Text(
            text = "Device 2",
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,

        )
        ShapeGreen()
        Text(
            text = "Device 3",
            modifier = Modifier.padding(top = 30.dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        ShapeGreen()
    }
}