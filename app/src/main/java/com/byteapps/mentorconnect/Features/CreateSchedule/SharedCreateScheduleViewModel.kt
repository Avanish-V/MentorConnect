package com.byteapps.mentorconnect.Features.CreateSchedule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.sql.Time
import kotlin.random.Random

class SharedCreateScheduleViewModel : ViewModel() {


    private val _schedules: MutableStateFlow<List<DaySlotModel>> = MutableStateFlow(emptyList())
    val schedules: StateFlow<List<DaySlotModel>> get() = _schedules


    private val _setTimeSlotData: MutableStateFlow<SetTimeSlotData> = MutableStateFlow(SetTimeSlotData())
    val setTimeSlotData: StateFlow<SetTimeSlotData> get() = _setTimeSlotData


    fun addSchedule(selectedDate: String, scheduleId: String) {

        val newSchedule = DaySlotModel(
            daySlotId = scheduleId,
            date = selectedDate,
            isActive = false,
            timeSlot = listOf()
        )
        _schedules.value += newSchedule

    }

    fun addTimeSlot(start:String?=null,end:String?=null,timeSlotId: String, daySlotIdId: String) {

        val newTimeSlot = TimeSlotModel(
            timeSlotId = timeSlotId,
            start = start,
            end = end
        )

        val updatedSchedules = _schedules.value.map { daySlotData ->
            if (daySlotData.daySlotId == daySlotIdId) {
                daySlotData.copy(timeSlot = (daySlotData.timeSlot ?: emptyList()) + newTimeSlot)
            } else {
                daySlotData
            }
        }

        _schedules.value = updatedSchedules
    }

    fun deleteTimeSlot(timeSlotId: String, scheduleId: String) {
        val updatedSchedules = _schedules.value.map { schedule ->
            if (schedule.daySlotId == scheduleId) {
                schedule.copy(timeSlot = schedule.timeSlot?.filterNot { it.timeSlotId == timeSlotId })
            } else {
                schedule
            }
        }

        _schedules.value = updatedSchedules
    }

    fun generateId():String{
        return (100..999).random().toString()
    }

    fun updateTimeSlot(selectedDate: String, timeSlotId: String, daySlotId: String, selectorID: Int) {
        // Create a new TimeSlotModel based on the selectorID
        val newTimeSlot = if (selectorID == 1) {
            TimeSlotModel(
                timeSlotId = timeSlotId,
                start = selectedDate,
                end = null // Clear 'end' when selecting 'start'
            )
        } else {
            TimeSlotModel(
                timeSlotId = timeSlotId,
                start = null, // Clear 'start' when selecting 'end'
                end = selectedDate
            )
        }

        // Update the schedules list by mapping through it and updating the matching daySlotId
        val updatedSchedules = _schedules.value.map { daySlotData ->
            if (daySlotData.daySlotId == daySlotId) {
                // Update the timeSlot list by clearing opposite fields if they exist
                val updatedTimeSlots = (daySlotData.timeSlot ?: emptyList()).map { existingSlot ->
                    if (existingSlot.timeSlotId == timeSlotId) {
                        // Replace the existing slot with new start or end, setting the other to null
                        if (selectorID == 1) {
                            existingSlot.copy(start = selectedDate)
                        } else {
                            existingSlot.copy(end = selectedDate)
                        }
                    } else {
                        existingSlot
                    }
                }

                // Check if the timeSlotId exists in the current list and add new if not present
                if (daySlotData.timeSlot?.any { it.timeSlotId == timeSlotId } != true) {
                    daySlotData.copy(timeSlot = updatedTimeSlots + newTimeSlot)
                } else {
                    daySlotData.copy(timeSlot = updatedTimeSlots)
                }
            } else {
                daySlotData
            }
        }

        // Update the state with the new list of schedules
        _schedules.value = updatedSchedules
    }



    fun setTimeSlotData(daySlotId:String,timeSlotId: String,selectorID:Int){
        _setTimeSlotData.value = SetTimeSlotData(
            daySlotId = daySlotId,
            timeSlotId = timeSlotId,
            selectorID = selectorID
        )
    }


}


data class SetTimeSlotData(
    val daySlotId: String = "",
    val timeSlotId:String = "",
    val selectorID: Int = 0
)
data class DaySlotModel(
    val daySlotId:String = "",
    val date:String = "",
    val isActive:Boolean = false,
    var timeSlot: List<TimeSlotModel> ?= null
)
data class TimeSlotModel(
    val timeSlotId:String,
    val start:String?=null,
    val end:String?= null
)

data class UpdateTimeSlotModel(
    val daySlotId: String = "",
    val timeSlotId:String = "",
    val start:String?=null,
    val end:String?= null
)