package com.example.aquaguardianapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



// Co-Pilot was used for this project

// Navigation code source
//https://github.com/philipplackner/NestedNavigationGraphsGuide.git

// Bluetooth code source
//https://github.com/DrVipinKumar/Android-Jetpack-Compose/tree/main/BluethoothJetpackCompose

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardianAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MyApp()
                }
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
                    OnboardingScreen(onContinueClicked = { navController.navigate("login") },
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
                        logout = { navController.navigate("login") },
                        activeDevicesClicked = { navController.navigate("activeDevices") },
                        addDevicesClicked = { navController.navigate("addDevices") },
                        locationClicked = { navController.navigate("locations") })

                }
                composable("activeDevices") {
                    activeDevice(backButton = { navController.navigate("mainMenu") })
                }
                composable("addDevices") {
                    addDevices(backButton = { navController.navigate("mainMenu") })
                }
                composable("locations") {
                    locations(backButton = { navController.navigate("mainMenu") })
                }
            }
        }
    }


    @Composable
    fun OnboardingScreen(
        onContinueClicked: () -> Unit,
        registerClicked:() -> Unit,
        modifier: Modifier = Modifier
    ) {

        Column(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aqua Guardian",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )

        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Aqua Guardian Logo",
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .size(400.dp)

            )
            Button(
                modifier = Modifier.padding(top = 40.dp),
                onClick = onContinueClicked
            ) {
                Text(
                    text = "Login",
                    fontSize = 50.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive
                )

            }
            Button(
                modifier = Modifier.padding(bottom = 100.dp, top = 4.dp),
                onClick = registerClicked
            ) {
                Text(
                    text = "Register",
                    fontSize = 30.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive

                )
            }
        }
        Column(
            modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Guarding Your Hydration",
                modifier = Modifier.padding(bottom = 60.dp,top = 30.dp),
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PasswordTextField() {
        var password by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .height(50.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun confirmPasswordTextField() {
        var password by rememberSaveable { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .height(50.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Confirm password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun loginTextBox() {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Username") },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun emailTextBox() {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Email") },
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainMenu(
        logout: () -> Unit,
        activeDevicesClicked: () -> Unit,
        addDevicesClicked: () -> Unit,
        locationClicked: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(title = {
                Text(
                    text = "Main Menu",
                    fontSize = 50.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )

            })
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Button(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                onClick = { activeDevicesClicked() }
            ) {
                Text(
                    text = "Active Devices",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )

            }
            Button(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                onClick = { addDevicesClicked() }
            ) {
                Text(
                    text = "Add Device",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Serif
                )

            }
            Button(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                onClick = { locationClicked() }
            ) {
                Text(
                    text = "Location",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )

            }
            Button(
                onClick = { logout() },
                modifier = Modifier.padding(top = 120.dp)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 40.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive
                )

            }
        }
    }

    @Composable
    fun Logins(
        loginSuccess: () -> Unit,
        modifier: Modifier = Modifier,

        ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aqua Guardian",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Aqua Guardian Logo",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(200.dp)

            )

            loginTextBox()
            Spacer(modifier = Modifier.size(10.dp))
            PasswordTextField()
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = { loginSuccess() }
            ) {
                Text(
                    text = "Login",
                    fontSize = 40.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }


    @Composable
    fun Registers(
        registerSuccess: () -> Unit,
        modifier: Modifier = Modifier,

        ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Aqua Guardian Logo",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(200.dp)

            )

            loginTextBox()
            Spacer(modifier = Modifier.size(10.dp))
            PasswordTextField()
            Spacer(modifier = Modifier.size(10.dp))
            confirmPasswordTextField()
            Spacer(modifier = Modifier.size(10.dp))
            emailTextBox()
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = { registerSuccess() }
            ) {
                Text(
                    text = "Register",
                    fontSize = 40.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    fontFamily = FontFamily.Cursive
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun activeDevice(
        backButton: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(title = {
                Text(
                    text = "Active Device",
                    fontSize = 50.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
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
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            )
            Text(
                text = "Device 2",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = "Device 3",
                modifier = Modifier.padding(top = 30.dp),
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White,
            )

        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addDevices(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = {
            Text(text = "Add Device",
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive)
        },
            navigationIcon = {
                IconButton(onClick = { backButton() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            })
    }
}


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun locations(
        backButton: () -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(title = {
                Text(
                    text = "Locations",
                    fontSize = 50.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    fontFamily = FontFamily.Cursive
                )
            },
                navigationIcon = {
                    IconButton(onClick = { backButton() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                })
        }
    }
    @Preview
    @Composable
    fun MyAppPreview() {
        AquaGuardianAppTheme {
            MyApp(Modifier.fillMaxSize())
        }
    }

    @Preview
    @Composable
    fun MainMenuPreview() {
        AquaGuardianAppTheme {
            MainMenu(
                logout = {},
                activeDevicesClicked = {},
                addDevicesClicked = {},
                locationClicked = {})

        }
    }
}
