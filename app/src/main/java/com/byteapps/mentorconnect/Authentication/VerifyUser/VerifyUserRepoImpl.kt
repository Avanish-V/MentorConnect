package com.byteapps.mentorconnect.Authentication.VerifyUser

import com.google.firebase.auth.FirebaseAuth
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class VerifyUserRepoImpl(val httpClient: HttpClient):VerifyUserRepo {


    override fun verifyUserByToken(token: String): Flow<Boolean> {
        return callbackFlow {

        }
    }


}