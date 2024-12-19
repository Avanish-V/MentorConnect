package com.byteapps.mentorconnect.Utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.byteapps.mentorconnect.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarSelector(
    isVisible: MutableState<Boolean>,
    onConfirm: (String) -> Unit,
    onDismiss: (Boolean) -> Unit
) {
    // Remember the DatePicker state
    val datePickerState = rememberDatePickerState()

    // Check if the dialog should be visible
    if (isVisible.value) {
        DatePickerDialog(
            onDismissRequest = { onDismiss(false) },
            confirmButton = {
                Button(
                    modifier = Modifier.padding(end = 12.dp, bottom = 12.dp),
                    onClick = {
                        // Confirm selection and pass the selected date
                        if (datePickerState.selectedDateMillis != null) {
                            onConfirm(timeMillsToString(datePickerState.selectedDateMillis!!))
                            isVisible.value = false
                        }
                    },
                    enabled = datePickerState.selectedDateMillis != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colorScheme.primary,

                    )

                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        onDismiss(false)
                        isVisible.value = false // Close the dialog
                    },
                    border = BorderStroke(
                        width = 1.dp,
                        color = AppTheme.colorScheme.primary
                    ),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = AppTheme.colorScheme.primary,
                        containerColor = Color.Transparent
                    )
                ) {
                    Text("Close")
                }
            },
            shape = RoundedCornerShape(20.dp),
            tonalElevation = 5.dp,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White
            )
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = true,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                    selectedDayContentColor = Color.White,
                    selectedDayContainerColor = AppTheme.colorScheme.primary,
                    todayDateBorderColor = AppTheme.colorScheme.primary,
                    todayContentColor = AppTheme.colorScheme.primary
                )

            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
    isTimePickerActive : Boolean,
    onConfirm: (String) -> Unit,
    onDismiss: (Boolean) -> Unit,
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = false,
    )

    if (isTimePickerActive){
        Column {

            DatePickerDialog(
                onDismissRequest = {onDismiss(false) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedHour = timePickerState.hour
                            val selectedMinute = timePickerState.minute

                            // Convert hour to 12-hour format
                            val isPM = selectedHour >= 12
                            val formattedHour = if (selectedHour % 12 == 0) 12 else selectedHour % 12
                            val formattedMinute = String.format("%02d", selectedMinute)
                            val amPm = if (isPM) "pm" else "am"

                            val formattedTime = "$formattedHour:$formattedMinute$amPm"
                            onConfirm(formattedTime)
                        },

                        ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onDismiss(false)
                        }
                    ) {
                        Text("Close")
                    }
                },
                shape = RoundedCornerShape(20.dp),
                tonalElevation = 5.dp,
            ) {

                Box(modifier = Modifier.fillMaxWidth().padding(top = 20.dp), contentAlignment = Alignment.Center){
                    TimePicker(state = timePickerState)
                }

            }

        }
    }

}

fun timeMillsToString(timeMills: Long): String {
    return try {
        val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
        val date = Date(timeMills)
        dateFormat.format(date)
    } catch (e: Exception) {
        "Invalid date"
    }
}