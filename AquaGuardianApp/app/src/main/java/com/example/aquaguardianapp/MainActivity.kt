package com.example.aquaguardianapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController


// Co-Pilot was used for this project

// Navigation code source
//https://github.com/philipplackner/NestedNavigationGraphsGuide.git

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaGuardianAppTheme {
            }
        }
    }
}
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val whenStarting = navController.currentBackStackEntry?.destination?.route == "OnboardingScreen" // if the current route is the onboarding screen, then we are starting the app for the first time

    Surface(modifier = modifier.fillMaxSize(), color = (Color(0xFF00C2FF))) {
        if (whenStarting) {
            OnboardingScreen(onContinueClicked = { navController.navigate("login") })  // if we are starting the app for the first time, then show the onboarding screen
        } else {
            NavHost(navController = navController, startDestination = "OnboardingScreen") { // otherwise show the main navigation graph
                composable("OnboardingScreen") {
                    OnboardingScreen(onContinueClicked = { navController.navigate("login") })
                }
                composable("login") {
                    Logins(loginSuccess = { navController.navigate("mainMenu") })
                }
                composable("mainMenu") {
                    MainMenu(logout = { navController.navigate("login") })
                }
            }
        }
    }
}
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Aqua Guardian",
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
             Text(text = "Login",
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive)

        }
        Button(
            modifier = Modifier.padding(vertical = 4.dp),
            onClick = onContinueClicked
        ) {
            Text(text ="Register",
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )
        }
    }
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text="Guarding Your Hydration",
            modifier = Modifier.padding(bottom = 60.dp),
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
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
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

@Composable
fun MainMenu(
    logout: () -> Unit,
    modifier: Modifier = Modifier,
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
    Column( modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(
            modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Active Devices",
                fontSize = 50.sp,
                fontFamily = FontFamily.Serif)

        }
        Button(
            modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Add Device",
                fontSize = 50.sp,
                fontFamily = FontFamily.Serif)

        }
        Button(
            modifier = Modifier.padding(top = 40.dp).fillMaxWidth(),
            onClick = {}
        ) {
            Text(text = "Location",
                fontSize = 50.sp,
                fontFamily = FontFamily.Serif)

        }
        Button(
            onClick = { logout() },
            modifier = Modifier.padding(top = 120.dp)
        ) {
            Text(text = "Logout",
                fontSize = 50.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                fontFamily = FontFamily.Cursive)

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
                fontFamily = FontFamily.Cursive
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    AquaGuardianAppTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    AquaGuardianAppTheme {
        Logins(loginSuccess = {})
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
        MainMenu(logout = {})
    }
}
