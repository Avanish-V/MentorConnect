package com.byteapps.mentorconnect.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.Features.CreateSchedule.SharedCreateScheduleViewModel
import com.byteapps.mentorconnect.Screens.SearchFlow.SingleScheduleDay
import com.byteapps.mentorconnect.Utils.CalendarSelector
import com.byteapps.mentorconnect.Utils.DialExample
import com.byteapps.mentorconnect.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScheduleScreen(navHostController: NavHostController) {
    val calenderIsVisible = remember { mutableStateOf(false) }
    val timePickerIsVisible = remember { mutableStateOf(false) }
    val sharedCreateScheduleViewModel: SharedCreateScheduleViewModel = viewModel()
    val schedules = sharedCreateScheduleViewModel.schedules.collectAsState()
    val slotData = sharedCreateScheduleViewModel.setTimeSlotData.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Schedules") },
                navigationIcon = {
                    IconButton(onClick = {navHostController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {

                    OutlinedButton(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = { calenderIsVisible.value = true },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = AppTheme.colorScheme.primary,
                        ),
                        border = BorderStroke(width = 1.dp, color = AppTheme.colorScheme.primary)
                    ) {
                        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = ""
                            )
                            Text("Create")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ){

        Surface(color = Color.White) {
            if (schedules.value.isNotEmpty()){
                LazyColumn(
                    Modifier.padding(it),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    items(schedules.value){schedule->

                        SingleScheduleDay(
                            slotList = schedule.timeSlot,
                            onAddSessionClick = {
                                sharedCreateScheduleViewModel.addTimeSlot(
                                    start = "",
                                    end = "",
                                    timeSlotId = sharedCreateScheduleViewModel.generateId(),
                                    daySlotIdId = schedule.daySlotId
                                )
                            },
                            onDeleteTimeSlotClick = { timeSlotId ->
                                sharedCreateScheduleViewModel.deleteTimeSlot(
                                    timeSlotId = timeSlotId,
                                    scheduleId = schedule.daySlotId
                                )
                            },
                            onTimeSetClick = {
                                timePickerIsVisible.value = it.isActive
                                sharedCreateScheduleViewModel.setTimeSlotData(
                                    daySlotId = schedule.daySlotId,
                                    timeSlotId = it.timeSlotId,
                                    selectorID = it.selectorId
                                )
                            },
                            selectedDate = schedule.date,
                            isChecked = {

                            }
                        )
                    }
                }
            }

            Box(modifier = Modifier.padding(it)){

                CalendarSelector(
                    isVisible = calenderIsVisible,
                    onConfirm = {sharedCreateScheduleViewModel.addSchedule(
                        selectedDate = it,
                        scheduleId = sharedCreateScheduleViewModel.generateId()
                    )},
                    onDismiss = {calenderIsVisible.value = false}
                )

                DialExample(
                    isTimePickerActive = timePickerIsVisible.value,
                    onConfirm = {
                        sharedCreateScheduleViewModel.updateTimeSlot(
                            selectedDate = it,
                            timeSlotId = slotData.value.timeSlotId,
                            selectorID = slotData.value.selectorID,
                            daySlotId = slotData.value.daySlotId
                        ).apply {
                            timePickerIsVisible.value = false
                        }
                    },
                    onDismiss = {timePickerIsVisible.value = false}
                )
            }
        }
    }
}