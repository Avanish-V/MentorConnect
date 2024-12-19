package com.byteapps.mentorconnect.Screens.SearchFlow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme
import com.byteapps.mentorconnect.ui.theme.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navHostController: NavHostController) {

    var searchValue by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.background(color = Background)) {

        SearchBar(

            inputField = {

                Row (verticalAlignment = Alignment.CenterVertically){

                    IconButton(onClick = { navHostController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = AppTheme.colorScheme.primary
                        )
                    }
                    OutlinedTextField(
                        modifier = Modifier.weight(1f).padding(end = 10.dp),
                        value = searchValue,
                        onValueChange = {searchValue = it},
                        placeholder = {
                            Text(
                                text = "Enter Mentor Name",
                                color = AppTheme.colorScheme.tertiary
                            )
                        },
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                        ),

                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = AppTheme.colorScheme.onBackground,
                            unfocusedContainerColor = AppTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,

                        )
                    )
                }

            },
            expanded = true,
            onExpandedChange = {

            },
            modifier = Modifier.border(
                width = 2.dp,
                color = AppTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(10.dp),
            colors = SearchBarDefaults.colors(
                containerColor = AppTheme.colorScheme.background
            )

        ) {

            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(10){
                    MentorSingleCard(
                        onClick = {
                            navHostController.navigate(Routes.Main.MentorProfile.routes)
                        }
                    )
                }
            }

        }

    }
}

@Composable
fun MentorSingleCard(onClick:()->Unit) {

    Card(
        onClick = {
            onClick.invoke()
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column (
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                ).padding(
                    13.dp
                ),
            verticalArrangement = Arrangement.spacedBy(13.dp)
        ){

            Row (horizontalArrangement = Arrangement.spacedBy(10.dp) ){
                Image(
                    modifier = Modifier.size(70.dp).clip(CircleShape),
                    painter = painterResource(R.drawable.cat),
                    contentDescription = null
                )

                Column (verticalArrangement = Arrangement.spacedBy(6.dp)){
                    Text("Nishant Chahar", style = AppTheme.typography.titleMedium)
                    Text("SDE at Microsoft", color = Color.Gray)
                }

            }

            Text("Building @Tayyari | Ex-Microsoft | 400k+ Subs on YT | NSIT")

        }

    }

}



