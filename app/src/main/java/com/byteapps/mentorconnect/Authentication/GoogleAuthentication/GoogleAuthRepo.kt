package com.byteapps.mentorconnect.Authentication.GoogleAuthentication

import android.content.Intent
import android.content.IntentSender

interface GoogleAuthRepo {

    suspend fun signIn():IntentSender?

    suspend fun signInWithIntent(intent:Intent):SignInResult

    fun getCurrentUser():Boolean

}