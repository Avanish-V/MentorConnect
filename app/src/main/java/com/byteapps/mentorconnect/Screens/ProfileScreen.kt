package com.byteapps.mentorconnect.Screens

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.byteapps.mentorconnect.Screens.SearchFlow.MentorshipCard
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", style = AppTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = AppTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )

            )
        },
        containerColor = Color.White
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = AppTheme.colorScheme.background
        ) {

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {

                    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Card(
                            Modifier.size(100.dp),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 2.dp,
                                color = AppTheme.colorScheme.primary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize().clip(CircleShape).padding(5.dp)) {
                                Image(
                                    painter = painterResource(R.drawable.cat),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Avanish Kumar",
                                    fontWeight = FontWeight.Bold,
                                    style = AppTheme.typography.titleLarge
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = AppTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                item {
                    HorizontalDivider()
                }

                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "Social Accounts",
                                fontWeight = FontWeight.Bold,
                                style = AppTheme.typography.titleMedium
                            )
                            HorizontalDivider(
                                modifier = Modifier.width(80.dp),
                                thickness = 3.dp,
                                color = AppTheme.colorScheme.primary
                            )
                        }

                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = AppTheme.colorScheme.primary
                            )
                        }
                    }

                }

                item {


                    Row(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Card(
                            onClick = {},
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(R.drawable.linkedin),
                                    contentDescription = null
                                )
                            }
                        }

                        Card(
                            onClick = {},
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(R.drawable.instagram),
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }

                item {
                    HorizontalDivider()
                }

                item {

                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(
                                    text = "About",
                                    fontWeight = FontWeight.Bold,
                                    style = AppTheme.typography.titleMedium
                                )
                                HorizontalDivider(
                                    modifier = Modifier.width(30.dp),
                                    thickness = 3.dp,
                                    color = AppTheme.colorScheme.primary
                                )
                            }

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = AppTheme.colorScheme.primary
                                )
                            }
                        }

                        Text(
                            "Building @Tayyari | Ex-Microsoft | 400k+ Subs on YT |\n" +
                                    "NSIT"
                        )
                    }

                }

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