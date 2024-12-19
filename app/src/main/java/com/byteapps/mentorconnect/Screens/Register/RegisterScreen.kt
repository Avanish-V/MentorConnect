package com.byteapps.mentorconnect.Screens.Register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.Routes

@Composable
fun SignInScreen(navHostController: NavHostController) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.instructor),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White),
                        startY = 0.0f,
                        endY = 1700.0f
                    ),),
            verticalArrangement = Arrangement.Bottom
        ) {


            Column (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ){
                Text("Welcome to\nMentor Connect", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.Black, textAlign = TextAlign.Center)
                Text("Connect with mentors and grow your career.", textAlign = TextAlign.Center, color = Color.Gray)
            }

            OutlinedButton(
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 100.dp).fillMaxWidth().height(52.dp),
                onClick = { navHostController.navigate(Routes.Register.CreateProfile.routes) },
                border = BorderStroke(width = 1.dp, color = Color.LightGray)

            ) {
                Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.google),
                        contentDescription = "",

                    )
                    Text("Continue with google", color = Color.Black)
                }

            }
        }

    }
}