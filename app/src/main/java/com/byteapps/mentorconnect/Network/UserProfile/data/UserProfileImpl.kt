package com.byteapps.mentorconnect.Network.UserProfile.data

import android.util.Log
import com.byteapps.mentorconnect.Network.UserProfile.domain.UserProfileRepo
import com.byteapps.mentorconnect.Utils.ResultState
import com.byteapps.mentorconnect.Utils.getAuthToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserProfileImpl(private val httpClient: HttpClient,private val idToken:String):UserProfileRepo {

    override suspend fun getBaseProfile(): Flow<ResultState<UserBasicProfileDTO>> {
        return callbackFlow {
            trySend(ResultState.Loading)

            try {
                // Fetch response
                val response = httpClient.get("/user-profile/3yNCEYtyXIhpzC8J8TipyaOlpA02")
                val responseBody = response.body<UserBasicProfileDTO>()

                // Handle HTTP status codes
                when (response.status.value) {
                    200 -> {
                        trySend(ResultState.Success(responseBody))
                    }
                    else -> {
                        trySend(ResultState.Error("Unexpected status code: ${response.status.value}"))
                    }
                }
            } catch (e: IOException) {
                // Log and send IO exceptions
                Log.e("ERROR_IS", "IOException: ${e.message}", e)
                trySend(ResultState.Error("Network error: ${e.message}"))
            } catch (e: Exception) {
                // Log and send general exceptions
                Log.e("ERROR_IS", "Exception: ${e.message}", e)
                trySend(ResultState.Error("An error occurred: ${e.message}"))
            } finally {
                // Ensure completion and cleanup if needed
                awaitClose { Log.d("getBaseProfile", "Flow closed") }
            }
        }
    }

    override suspend fun createMentorProfile(mentorProfile:MentorProfileDTO): Flow<ResultState<Boolean>> {
        return callbackFlow {

            trySend(ResultState.Loading)

            try {

                Log.d("MENTOR_PROFILE",mentorProfile.toString())

                getAuthToken().let {

                }

                val response = httpClient.patch("/user-profile/create-mentor"){
                    contentType(ContentType.Application.Json)
                    headers {
                        append("Authorization", "Bearer ${idToken}")
                    }
                    setBody(mentorProfile)
                }
                when (response.status.value) {
                    200 -> {
                        trySend(ResultState.Success(true))
                    }
                    else -> {
                        trySend(ResultState.Error("Unexpected status code: ${response.status.value}"))
                    }
                }
            } catch (e: IOException) {
                // Log and send IO exceptions
                Log.e("ERROR_IS", "IOException: ${e.message}", e)
                trySend(ResultState.Error("Network error: ${e.message}"))
            } catch (e: Exception) {
                // Log and send general exceptions
                Log.e("ERROR_IS", "Exception: ${e.message}", e)
                trySend(ResultState.Error("An error occurred: ${e.message}"))
            } finally {
                // Ensure completion and cleanup if needed
                awaitClose { Log.d("getBaseProfile", "Flow closed") }
            }
        }
    }

    @OptIn(InternalAPI::class)
    override fun updateSocialAccounts(accounts: String): Flow<ResultState<Boolean>> {
        return callbackFlow {

            trySend(ResultState.Loading)

            try {

                val response = httpClient.patch("/user-profile/update-social"){
                    ContentType.Application.Json
//                    headers {
//                        append("Authorization", "Bearer $idToken")
//                    }
                    body = FormDataContent(Parameters.build {
                        append("social",accounts)
                    })
                }

                when(response.status.value){
                    200->{
                        trySend(ResultState.Success(true))
                    }
                }

            }catch (e:IOException){
                trySend(ResultState.Error("Check Your Network"))
            }

            awaitClose()

        }
    }

    @OptIn(InternalAPI::class)
    override fun updateUserName(userName: String): Flow<ResultState<Boolean>> {
        return callbackFlow {

            trySend(ResultState.Loading)

            try {


                val response = httpClient.patch("/user-profile/update-username"){
                    ContentType.Application.Json
//                    headers {
//                        append("Authorization", "Bearer ${idToken}")
//                    }
                    body = FormDataContent(Parameters.build {
                        append("userName", userName)
                    })
                }

                when(response.status.value){
                    200->{
                        trySend(ResultState.Success(true))
                    }
                }

            }catch (e:IOException){
                trySend(ResultState.Error("Check Your Network"))
            }

            awaitClose()

        }
    }

    @OptIn(InternalAPI::class)
    override fun updateAbout(about: String): Flow<ResultState<Boolean>> {
        return callbackFlow {

            trySend(ResultState.Loading)

            try {

                val response = httpClient.patch("/user-profile/update-about"){
                    ContentType.Application.Json
//                    headers {
//                        append("Authorization", "Bearer $idToken")
//                    }
                    body = FormDataContent(Parameters.build {
                        append("userAbout", about)
                    })
                }

                when(response.status.value){
                    200->{
                        trySend(ResultState.Success(true))
                    }
                }

            }catch (e:IOException){
                trySend(ResultState.Error("Check Your Network"))
            }

            awaitClose()
        }
    }

}