import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addDevices(
    backButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("About Device", "Add Device page called.")


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { backButton() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text("About Device",
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
                    .background(Color(0xFF00C2FF)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            { Box(modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF00C2FF)).padding(40.dp),)
            {}
                Box(modifier = modifier
                    .fillMaxWidth().shadow(8.dp)
                    .background(Color(0xFFF0F0F0)).padding(30.dp),) {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Device Name: ",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )
                    Text(
                        modifier = Modifier.padding(top = 45.dp),
                        text = "Joe's Device",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize =15.sp,
                    )
                }
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C2FF)).padding(10.dp),)
                {}
                Box(modifier = modifier
                    .fillMaxWidth().shadow(8.dp)
                    .background(Color(0xFFF0F0F0)).padding(30.dp),) {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Tutorial Video: ",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )
                    videoPlayer(
                        modifier = Modifier.padding(top = 60.dp),
                        fullText = "Click Here for the Tutorial Videos",
                        hyperLinks = mapOf("Click Here for the Tutorial Videos" to "https://www.youtube.com/channel/UCaSgqHV2smbgLjSOnEmEpYw")
                    )
                }
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C2FF)).padding(10.dp),)
                {}
                Box(modifier = modifier
                    .fillMaxWidth().shadow(8.dp)
                    .background(Color(0xFFF0F0F0)).padding(30.dp),) {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Check Device Status:",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )

                    videoPlayer(
                        modifier = Modifier.padding(top = 60.dp),
                        fullText = "AquaGuardian.Online.",
                        hyperLinks = mapOf("AquaGuardian.Online" to "http://www.aquaguardian.online/")
                    )

                }
                Box(modifier = modifier
                    .fillMaxWidth()
                    .background(Color(0xFF00C2FF)).padding(10.dp),)
                {}
                Box(modifier = modifier
                    .fillMaxWidth().shadow(10.dp)
                    .background(Color(0xFFF0F0F0)).padding(40.dp),) {
                    Text(
                        modifier = Modifier.padding(top = 0.dp),
                        text = "Contact Us",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    )
                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        text = "aquaguardianscontact@gmail.com",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                }
            }
        }
    )
}
@Composable
fun videoPlayer(
    modifier: Modifier = Modifier,
    fullText: String,
    hyperLinks: Map<String, String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Bold,
    linkTextDecoration: TextDecoration = TextDecoration.None,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        for((key, value) in hyperLinks){

            val startIndex = fullText.indexOf(key)
            val endIndex = startIndex + key.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}



