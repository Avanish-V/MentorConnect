package com.byteapps.mentorconnect.Screens.BeAnExpert

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.byteapps.mentorconnect.Network.UserProfile.data.Experience
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpertFormSharedViewModel:ViewModel() {

    private val _experience = MutableStateFlow<String>("")
    val experience = _experience.asStateFlow()

    private val _company = MutableStateFlow<String>("")
    val company = _company.asStateFlow()

    private val _currentRole = MutableStateFlow<String>("")
    val currentRole = _company.asStateFlow()

    private val _description = MutableStateFlow<String>("")
    val description = _description.asStateFlow()


    fun setExperience(experience:String){
        _experience.value = experience
    }
    fun setCompany(company:String){
        _company.value = company
    }
    fun setCurrentRole(currentRole:String){
        _currentRole.value = currentRole
    }
    fun setDescription(description:String){
        _description.value = description
    }


    private val _expertise = MutableStateFlow<List<String>>(emptyList())
    val expertise = _expertise.asStateFlow()

    fun setExpertise(expertise: String) {
        _expertise.value += expertise // Create a new list with added item
    }

    fun deleteExpertise(expertise: String) {
        _expertise.value = _expertise.value.filter { it != expertise } // Create a new list without the removed item
    }





}