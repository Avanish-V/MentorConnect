package com.byteapps.mentorconnect.Network.UserProfile.domain

import com.byteapps.mentorconnect.Network.UserProfile.data.MentorProfileDTO
import com.byteapps.mentorconnect.Network.UserProfile.data.UserBasicProfileDTO
import com.byteapps.mentorconnect.Utils.ResultState
import kotlinx.coroutines.flow.Flow

interface UserProfileRepo {



    suspend fun getBaseProfile():Flow<ResultState<UserBasicProfileDTO>>

    suspend fun createMentorProfile(mentorProfile: MentorProfileDTO):Flow<ResultState<Boolean>>

    fun updateUserName(userName:String):Flow<ResultState<Boolean>>

    fun updateSocialAccounts(accounts:String):Flow<ResultState<Boolean>>

    fun updateAbout(about:String):Flow<ResultState<Boolean>>


}