package com.byteapps.mentorconnect.Authentication.VerifyUser

import kotlinx.coroutines.flow.Flow

interface VerifyUserRepo {
    fun verifyUserByToken(token:String):Flow<Boolean>
}