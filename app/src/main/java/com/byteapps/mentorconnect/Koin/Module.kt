package com.byteapps.mentorconnect.Koin

import android.util.Log
import com.byteapps.mentorconnect.Authentication.GoogleAuthentication.GoogleAuthRepo
import com.byteapps.mentorconnect.Authentication.GoogleAuthentication.GoogleAuthUiClient
import com.byteapps.mentorconnect.Authentication.GoogleAuthentication.GoogleSignInViewModel
import com.byteapps.mentorconnect.Network.UserProfile.data.UserProfileImpl
import com.byteapps.mentorconnect.Network.UserProfile.domain.UserProfileRepo
import com.byteapps.mentorconnect.Network.UserProfile.presentation.UserProfileViewModel
import com.byteapps.mentorconnect.Screens.BeAnExpert.ExpertFormSharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }

            install(io.ktor.client.plugins.logging.Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Error is$message")
                    }
                }
            }
            headers {
                append("Authorization", "Bearer ${provideFirebaseAuthToken(get())}")
            }

            defaultRequest {
                url("http://192.168.163.128:8080") // Base URL
                headers.append(HttpHeaders.Accept, "application/json")
            }
        }
    }

    // Provide Firebase Auth instance
    single<FirebaseAuth> { FirebaseAuth.getInstance() }

    // Provide Firebase Auth token
    CoroutineScope(Dispatchers.IO).launch {
        single { provideFirebaseAuthToken(get()) }

    }

    single<UserProfileRepo> { UserProfileImpl(get(),get()) }
    single<GoogleAuthRepo> { GoogleAuthUiClient(androidContext(), get()) }


    viewModel { GoogleSignInViewModel(get()) }
    viewModel { UserProfileViewModel(get()) }
    viewModel { ExpertFormSharedViewModel() }

}

  fun provideFirebaseAuthToken(firebaseAuth: FirebaseAuth):Flow<String> {
    return callbackFlow {
        firebaseAuth.currentUser?.getIdToken(true)?.result?.token
        close()
    }
}