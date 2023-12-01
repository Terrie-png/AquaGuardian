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
import com.example.aquaguardianapp.ConfirmPasswordTextField
import com.example.aquaguardianapp.PasswordTextField
import com.example.aquaguardianapp.R
import com.example.aquaguardianapp.EmailTextBox
import com.example.aquaguardianapp.LoginTextBox

@Composable
fun Registers(
    registerSuccess: () -> Unit,
    modifier: Modifier = Modifier,

    )
{
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
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
        PasswordTextField( password = password ) { password = it }
        Spacer(modifier = Modifier.size(10.dp))
        ConfirmPasswordTextField( confirmpassword = confirmpassword ) { confirmpassword = it }
        Spacer(modifier = Modifier.size(10.dp))
        EmailTextBox( email = email ) { email = it }

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
                if (username.isNotBlank() && password.isNotBlank()&& confirmpassword == password && email.isNotBlank()) {
                    registerSuccess()
                    Log.d("MainActivity", "Register Successful")

                } else if (username.isBlank()) {
                    errorMessage = "Please enter the username"
                    Log.d("MainActivity", "Invalid Register , username missing")

                } else if (password.isBlank()) {
                    errorMessage = "Please enter the password"
                    Log.d("MainActivity", "Invalid Register , password missing")

                } else if(confirmpassword != password){
                    errorMessage = "Passwords do not match"
                    Log.d("MainActivity", "Invalid Register , password miss matching")

                }
                else if(email.isBlank()){
                    errorMessage = "Please enter the email"
                    Log.d("MainActivity", "Invalid Register , email missing")

                }
                else {
                    errorMessage = "Information Invalid"
                    Log.d("MainActivity", "Invalid Register , missing all credentials")

                }
            }
        ){
            Text(
                text = "Register",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontFamily = FontFamily.Cursive
            )
        }
    }
}
