import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquaguardianapp.AnimatedShapeTouch
import com.example.aquaguardianapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    logout: () -> Unit,
    activeDevicesClicked: () -> Unit,
    addDevicesClicked: () -> Unit,
    locationClicked: () -> Unit,
    historyClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.aglogo),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(start = 5.dp)
                            .size(100.dp).shadow(10.dp)
                    )
                },
                title = {
                    Text("Main Menu",
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
            Button(
                modifier = Modifier
                    .padding(top = 40.dp).shadow(10.dp)
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
                    .padding(top = 40.dp).shadow(10.dp)
                    .fillMaxWidth(),
                onClick = { addDevicesClicked() }
            ) {
                Text(
                    text = "About Device",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Serif
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 40.dp).shadow(10.dp)
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
                modifier = Modifier
                    .padding(top = 40.dp).shadow(10.dp)
                    .fillMaxWidth(),
                onClick = { historyClicked() }
            ) {
                Text(
                    text = "History",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )
            }
            Spacer(modifier = Modifier.size(40.dp))
            AnimatedShapeTouch(logout = logout)
          }
        }
    )
}


