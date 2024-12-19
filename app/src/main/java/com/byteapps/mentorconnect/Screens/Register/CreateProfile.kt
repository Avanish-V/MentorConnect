package com.byteapps.mentorconnect.Screens.Register

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(navHostController: NavHostController) {

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = AppTheme.colorScheme.background
    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ){

            Box(modifier = Modifier.padding(top = 40.dp).fillMaxWidth(), contentAlignment = Alignment.CenterStart){
                Text(
                    text="Create Profile",
                    style = AppTheme.typography.titleLarge,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }

            Column (
                Modifier.weight(1f).padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(60.dp)
            ){



                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(
                            color = Color.White
                        ).border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ){
                    IconButton(
                        modifier = Modifier.size(52.dp),
                        onClick = {}
                    ) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            painter = painterResource(R.drawable.outline_camera_alt_24),
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }

                }


                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text("Your Name")
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = AppTheme.colorScheme.primary
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Text("😡", color = Color.Red)
                    }
                )

            }

            Button(
                onClick = {navHostController.navigate(Routes.Main.routes)},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.primary
                )
            ) {
                Text("Create")
            }



        }

    }

}

@Composable
fun SingleExperience(modifier: Modifier = Modifier) {

    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(13.dp)){

        Card (Modifier.size(60.dp)){
            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(R.drawable.cat),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column {
            Column() {
                Text("Software Engineer", fontWeight = FontWeight.Bold)
                Text("202-2024", color = Color.Gray)
                Text("Bengaluru, Karnataka", color = Color.Gray)
            }
            Text("Working on Live Persona Editor (LPE) and Live Persona Card (LPC) for Microsoft Teams and Outlook.")

        }


    }

}