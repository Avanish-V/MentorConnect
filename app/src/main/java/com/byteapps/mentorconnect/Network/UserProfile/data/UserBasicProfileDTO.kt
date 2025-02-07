package com.byteapps.mentorconnect.Network.UserProfile.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserBasicProfileDTO(
    @SerialName("_id") val _id: String,
    @SerialName("userName")val userName:String = "",
    @SerialName("userImage")val userImage:String = "",
    @SerialName("userEmail")val userEmail:String = "",
    @SerialName("userAbout")val userAbout:String = "",
    @SerialName("social")val social:String = "",
    @SerialName("metaData")val metaData:MetaData,
    @SerialName("mentorDetails")val mentorDetails:MentorProfileDTO? = null,
)


@Serializable
data class MetaData(
    @SerialName("isMentor")val isMentor:Boolean,
    @SerialName("isFirstUser")val isFirstUser:Boolean,
    @SerialName("createdAt")val createdAt:String?=null,
    @SerialName("updatedAt")val updatedAt:String?=null
)

@Serializable
data class MentorProfileDTO(
    @SerialName("experience") val experience:List<Experience>,
    @SerialName("expertise")val expertise:List<String>
)

@Serializable
data class Experience(
    @SerialName("currentRole")val currentRole:String = "",
    @SerialName("company")val company:String = "",
    @SerialName("experience")val experience:String = "",
    @SerialName("description")val description:String = ""
)
