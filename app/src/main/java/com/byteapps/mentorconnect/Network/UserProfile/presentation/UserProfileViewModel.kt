package com.byteapps.mentorconnect.Network.UserProfile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byteapps.mentorconnect.Network.UserProfile.data.MentorProfileDTO
import com.byteapps.mentorconnect.Network.UserProfile.data.UserBasicProfileDTO
import com.byteapps.mentorconnect.Network.UserProfile.domain.UserProfileRepo
import com.byteapps.mentorconnect.Utils.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userProfileRepo: UserProfileRepo):ViewModel() {


    private val _userBaseProfile: MutableStateFlow<UserBaseProfileResultState> = MutableStateFlow(UserBaseProfileResultState())
    val userBaseProfile: StateFlow<UserBaseProfileResultState> = _userBaseProfile.asStateFlow()

    fun getUserProfile(){
        viewModelScope.launch {
            userProfileRepo.getBaseProfile().collect{
                when(it){
                    is ResultState.Loading->{
                        _userBaseProfile.value = UserBaseProfileResultState(isLoading = true)
                    }
                    is ResultState.Success->{
                        Log.d("ERROR_IS", it.data.userName)
                        _userBaseProfile.value = UserBaseProfileResultState(baseProfileData = it.data)
                    }
                    is ResultState.Error->{
                        _userBaseProfile.value = UserBaseProfileResultState(error = it.message)
                    }
                }
            }
        }
    }

    init {
        getUserProfile()
    }

    fun modifyName(userName:String) = userProfileRepo.updateUserName(userName)

    fun modifyAbout(about:String) = userProfileRepo.updateAbout(about)

    fun modifySocialAccount(social:String) = userProfileRepo.updateSocialAccounts(social)

    suspend fun createMentorProfile(mentorProfileDTO: MentorProfileDTO) = userProfileRepo.createMentorProfile(mentorProfileDTO)


}


data class UserBaseProfileResultState(
    val isLoading:Boolean = false,
    val baseProfileData: UserBasicProfileDTO? = null,
    val error:String = ""
)
