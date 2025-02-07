package com.byteapps.mentorconnect.Authentication.GoogleAuthentication

data class SignInState(
    val isSignInSuccessful:Boolean = false,
    val signInError:String? = null
)
