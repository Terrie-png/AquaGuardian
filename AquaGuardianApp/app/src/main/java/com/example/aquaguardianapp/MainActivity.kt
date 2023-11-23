package com.example.aquaguardianapp

import MainMenu
import OnboardingScreen
import Registers
import addDevices
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aquaguardianapp.ui.theme.AquaGuardianAppTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import com.example.aquaguardianapp.ui.theme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.ui.draw.clip
import history
import kotlinx.coroutines.delay
import locations


// Co-Pilot was used for this project

// Navigation code source
//https://github.com/philipplackner/NestedNavigationGraphsGuide.git

// Animation
//https://github.com/cp-radhika-s/CoolButtonClickEffects/blob/main/app/src/main/java/com/app/click_effects/MainActivity.kt


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardianAppTheme { // A surface container using the 'background' color from the theme
                Surface( // on below line we are specifying modifier and color for our app
                    modifier = Modifier.fillMaxSize()
                ) {

                        // on below line we are display list view
                        // method to display our list view.
                        DisplayListView()
                    }
                }
            }
        Log.d("MainActivity", "onCreate called")
        }
    }

fun getJSONData(courseList: MutableList<String>, ctx: Context) {
    // on below line we are creating a retrofit  builder and passing our base url

    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonkeeper.com/b/")
        // on below line we are calling add Converter factory as Gson converter factory.

        .addConverterFactory(GsonConverterFactory.create())
        // at last we are building our retrofit builder.
        .build()

    // below line is to create an instance for our retrofit api class.
    val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

    // on below line we are calling a method to get all the courses from API.
    val call: Call<ArrayList<ListModal>> = retrofitAPI.getLanguages()

    // on below line we are calling method to enqueue and calling
    // all the data from array list.
    call!!.enqueue(object : Callback<ArrayList<ListModal>?> {
        override fun onResponse(
            call: Call<ArrayList<ListModal>?>,
            response: Response<ArrayList<ListModal>?>
        ) {
            // on below line we are checking if response is successful.
            if (response.isSuccessful) {
                // on below line we are creating a new list

                // on below line we are passing
                // our response to our list
                var lst: ArrayList<ListModal> = response.body()!!

                // on below line we are passing
                // data from lst to course list.
                for (i in 0 until lst.size) {
                    // on below line we are adding data to course list.
                    courseList.add(lst.get(i).languageName)
                }
                Log.d("MainActivity", "Api call successful")
            }
        }

        override fun onFailure(call: Call<ArrayList<ListModal>?>, t: Throwable) {
            Log.d("MainActivity", "Api call failed")

            // displaying an error message in toast
            Toast.makeText(ctx, "Fail to get the data..", Toast.LENGTH_SHORT)
                .show()
        }
    })
}

@Composable
fun DisplayListView() {
    val context = LocalContext.current
    // on below line we are creating and
    // initializing our array list
    val courseList = remember { mutableStateListOf<String>() }
    getJSONData(courseList, context)

    // on the below line we are creating a lazy column for displaying a list view.on below line we are calling lazy column for displaying listview.
    LazyColumn {
        // on below line we are populating
        // items for listview.
        items(courseList) { language ->
            // on below line we are specifying ui for each item of list view.
            // we are specifying a simple text for each item of our list view.
            Text(language, modifier = Modifier.padding(15.dp))
            // on below line we are specifying
            // divider for each list item
            Divider()
        }
    }
}


    @Composable
    fun MyApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        Surface(modifier = modifier.fillMaxSize(), color = (Color(0xFF00C2FF))) {
            NavHost(
                navController = navController,
                startDestination = "OnboardingScreen"

            ) { // otherwise show the main navigation graph
                composable("OnboardingScreen") {
                    OnboardingScreen(onContinueClicked = { navController.navigate("login")

                                                         },
                        registerClicked = { navController.navigate("register") })
                }
                composable("login") {
                    Logins(loginSuccess = { navController.navigate("mainMenu") })
                }
                composable("register"){
                    Registers(registerSuccess = { navController.navigate("mainMenu") })
                }
                composable("mainMenu") {
                    MainMenu(
                        logout = { navController.navigate("OnboardingScreen") },
                        activeDevicesClicked = { navController.navigate("activeDevices") },
                        addDevicesClicked = { navController.navigate("addDevices") },
                        historyClicked = { navController.navigate("historys") },
                        locationClicked = { navController.navigate("locations") })
                }
                composable("activeDevices") {
                    activeDevice(backButton = { navController.navigate("mainMenu") })
                }
                composable("addDevices") {
                    addDevices(backButton = { navController.navigate("mainMenu") })
                }
                composable("historys") {
                    history(backButton = { navController.navigate("mainMenu") })
                }
                composable("locations") {
                    locations(backButton = { navController.navigate("mainMenu") })
                }
            }
        }
    }


@Composable
fun AnimatedShapeTouch(logout: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val cornerRadius by animateDpAsState(targetValue = if (isPressed.value) 10.dp else 50.dp)
    var buttonClicked by remember { mutableStateOf(false) }
        Box(

            modifier = Modifier
                .background(color = Color.Red, RoundedCornerShape(cornerRadius))
                .size(100.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple()
                ){
                    buttonClicked = true
                }
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Logout",
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
        }

    LaunchedEffect(buttonClicked) {
        if (buttonClicked) {
            delay(1000)
            logout()
            buttonClicked = false
        }
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PasswordTextField( password : String, onPasswordChange : (String) -> Unit) {
        TextField(
            modifier = Modifier.height(50.dp),
            value = password,
            onValueChange = { onPasswordChange(it) },
                label = { Text("Enter password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextBox( username : String, onLoginChange : (String) -> Unit) {
    TextField(
        modifier = Modifier.height(50.dp),
        value = username,
        onValueChange = { onLoginChange(it) },
        label = { Text("Enter Username") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ConfirmPasswordTextField( confirmpassword : String, onConfirmPasswordChange : (String) -> Unit) {
        TextField(
            modifier = Modifier
                .height(50.dp),
            value = confirmpassword,
            onValueChange = { onConfirmPasswordChange (it) },
            label = { Text("Confirm password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EmailTextBox(email : String, onEmailChange : (String) -> Unit) {
        TextField(
            modifier = Modifier.height(50.dp),
            value = email,
            onValueChange = { onEmailChange(it) },
            label = { Text("Enter Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))
    }
    @Preview
    @Composable
    fun MyAppPreview() {
        AquaGuardianAppTheme {
            MyApp(Modifier.fillMaxSize())
        }
    }

