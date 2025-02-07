package com.byteapps.mentorconnect.Screens.SearchFlow

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
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
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.sp
import com.byteapps.mentorconnect.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBookingScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = {navHostController.navigate(Routes.Main.Dashboard.routes)}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colorScheme.background,
                    navigationIconContentColor = AppTheme.colorScheme.primary,
                    titleContentColor = AppTheme.colorScheme.onTertiary
                )
            )
        },
        containerColor = AppTheme.colorScheme.background
    ) {

        Column (modifier = Modifier
            .padding(it)
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

            Text(
                text = "Book a mentoring Session",
                style = AppTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = AppTheme.colorScheme.onTertiary
            )

            Column (
                modifier = Modifier

                    .fillMaxWidth()

                    .border(
                        width = 1.dp,
                        color = AppTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(12.dp)
                    )
            ){

                Row (modifier = Modifier.padding(13.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.booking),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            color = AppTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "Select Date",
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colorScheme.onTertiary
                    )
                }

                LazyRow (contentPadding = PaddingValues(13.dp), horizontalArrangement = Arrangement.spacedBy(13.dp)){
                    items(getNextSevenDays()){
                        Box(
                            modifier = Modifier
                                .height(80.dp)
                                .width(60.dp)
                                .border(
                                    width = 1.dp,
                                    color = AppTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Column (verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally){
                                Text(
                                    text = it.day,
                                    color = AppTheme.colorScheme.onTertiary
                                )
                                Text(
                                    text = it.dayOfMonth.toString(),
                                    style = AppTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = AppTheme.colorScheme.onTertiary
                                )
                            }
                        }
                    }
                }
            }


            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = AppTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(12.dp)
                    )
            ){

                Row (modifier = Modifier.padding(13.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.time_watch_calendar),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            color = AppTheme.colorScheme.primary
                        )

                    )
                    Text(
                        text = "Select Time Slot",
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colorScheme.onTertiary
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(13.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    items(7){

                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = AppTheme.colorScheme.onPrimary,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text="8:00AM - 9:00AM",
                                modifier = Modifier.padding(13.dp),
                                color = AppTheme.colorScheme.onTertiary
                            )
                        }

                    }
                }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = AppTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(12.dp)
                    )
            ){

                Row (modifier = Modifier.padding(13.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    Text(
                        text="Session Details",
                        style = AppTheme.typography.titleMedium,
                        color = AppTheme.colorScheme.onTertiary
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f).padding(13.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(text = "Selected Date & Time", color = AppTheme.colorScheme.onTertiary)
                        Text(text = "8 Sunday,\n09:00PM", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.onTertiary)
                    }
                    Column (
                        modifier = Modifier.weight(1f).padding(13.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.End
                    ){
                        Text(text = "Duration", color = AppTheme.colorScheme.onTertiary)
                        Text(text = "1 Hour", style = AppTheme.typography.titleMedium, color = AppTheme.colorScheme.onTertiary)
                    }
                }




            }


            Button(
                onClick = {navHostController.navigate(Routes.Main.routes)},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.primary
                )
            ) {
                Text("Confirm Booking")
            }


        }

    }
}




fun getNextSevenDays(): List<Day> {
    val days = mutableListOf<Day>()
    val calendar = Calendar.getInstance()
    for (i in 0 until 7) {
        val date = calendar.time
        days.add(
            Day(
                date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date),
                day = SimpleDateFormat("EEE", Locale.getDefault()).format(date),
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            )
        )
        calendar.add(Calendar.DATE, 1)
    }
    return days
}

fun getFormattedDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date!!)
}

data class Day(
    val date: String,
    val day: String,
    val dayOfMonth: Int
)

