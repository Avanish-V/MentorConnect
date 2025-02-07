package com.byteapps.mentorconnect.Authentication.GoogleAuthentication

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException


class GoogleAuthUiClient(private val context: Context,private val httpClient: HttpClient):GoogleAuthRepo {

    private val oneTapClient:SignInClient = Identity.getSignInClient(context)

    private val auth=Firebase.auth

    override suspend fun signIn():IntentSender?{
        val result= try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null

        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun signInWithIntent(intent:Intent):SignInResult{
        var success_status:Boolean = false
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)
        return try {

            val user = auth.signInWithCredential(googleCredentials).await().user
            Log.d("TOKEN_ID1", user?.uid.toString())
            Log.d("TOKEN_ID2", googleIdToken.toString())


         user?.getIdToken(true)
                ?.addOnCompleteListener(OnCompleteListener<GetTokenResult> { task ->
                    if (task.isSuccessful) {
                        val idToken = task.result.token
                        CoroutineScope(Dispatchers.IO).launch {
                          val result =   verifyUserByToken(idToken.toString())
                            success_status = result?.status ?: false
                            Log.d("TOKEN_ID4", idToken.toString())
                        }
                        Log.d("TOKEN_ID4", "Closed")

                    } else {
                        // Handle error -> task.getException();
                    }
                })

            Log.d("TOKEN_ID3", googleIdToken.toString())

            SignInResult(
                status = user.run { success_status },
                errorMessage = null
            )

        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
           SignInResult(
               status = null,
               errorMessage = e.message
           )
        }
    }

    private fun buildSignInRequest():BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.Builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("294461340505-efsev8jdhgp51e5oehf47nl97honru1p.apps.googleusercontent.com")
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }

    suspend fun signOut(){
        try {
            auth.signOut()
        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    override fun getCurrentUser():Boolean{
        return FirebaseAuth.getInstance().currentUser?.uid != null
    }

    private suspend fun verifyUserByToken(token: String):UserResponse? {
        return try {
            val response = httpClient.post("/verifyUserByToken/$token") {
                contentType(ContentType.Application.Json)
            }

            // Deserialize the JSON response into a UserResponse object
            if (response.status == HttpStatusCode.OK) {
                val userResponse: UserResponse = response.body()
                Log.d("NetworkResponse", "Success: $userResponse")
                userResponse
            } else {
                Log.e("NetworkResponse", "Unexpected Response: ${response.status}")
                null
            }
        } catch (e: Exception) {
            Log.e("NetworkResponse", "Error: ${e.message}")
            null
        }
    }



}