package com.byteapps.mentorconnect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.byteapps.mentorconnect.Authentication.GoogleAuthentication.GoogleAuthUiClient
import com.byteapps.mentorconnect.Authentication.GoogleAuthentication.GoogleSignInViewModel
import com.byteapps.mentorconnect.Koin.appModule
import com.byteapps.mentorconnect.Network.UserProfile.presentation.UserProfileViewModel
import com.byteapps.mentorconnect.Screens.BeAnExpert.ExpertFormScreen
import com.byteapps.mentorconnect.Screens.CreateScheduleScreen
import com.byteapps.mentorconnect.Screens.MainScreen
import com.byteapps.mentorconnect.Screens.ProfileScreen
import com.byteapps.mentorconnect.Screens.Register.CreateProfileScreen
import com.byteapps.mentorconnect.Screens.Register.SignInScreen
import com.byteapps.mentorconnect.Screens.SearchFlow.CreateBookingScreen
import com.byteapps.mentorconnect.Screens.SearchFlow.MentorProfile
import com.byteapps.mentorconnect.Screens.SearchFlow.SearchScreen
import com.byteapps.mentorconnect.Screens.ServiceProducts.ServiceProductListScreen
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.koinInject
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin{
            androidContext(this@MainActivity)
            modules(appModule)
        }

       installSplashScreen()

        setContent {

            val navHostController = rememberNavController()
            val userProfileViewModel = koinInject<UserProfileViewModel>()
            val googleAuthViewModel = koinInject<GoogleSignInViewModel>()

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    NavHost(
                        modifier = Modifier.background(Color.White),
                        navController = navHostController,
                        startDestination = if (googleAuthViewModel.currentUser()) Routes.Main.routes else Routes.Register.routes
                    ) {

                        navigation(
                            startDestination = Routes.Register.SignIn.routes,
                            route = Routes.Register.routes
                        ){
                            composable(route = Routes.Register.SignIn.routes) {

                                val state = googleAuthViewModel.state.collectAsStateWithLifecycle()

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = {result->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                googleAuthViewModel.onSignInResult(
                                                    intent = result.data?:return@launch
                                                )

                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.value.isSignInSuccessful) {
                                    if (state.value.isSignInSuccessful){
                                        navHostController.navigate(Routes.Main.Dashboard.routes)
                                    }
                                }
                                
                                SignInScreen(
                                    navHostController = navHostController,
                                    onSignInClick = {

                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthViewModel.startSignIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender?:return@launch
                                                ).build()
                                            )
                                        }

                                    }
                                )



                            }
                            composable(route = Routes.Register.CreateProfile.routes) {
                                CreateProfileScreen(navHostController = navHostController)
                            }

                        }

                        navigation(
                            startDestination = Routes.Main.Dashboard.routes,
                            route = Routes.Main.routes
                        ) {
                            composable(route = Routes.Main.Dashboard.routes) {
                                MainScreen(
                                    navHostController = navHostController,
                                    userProfileViewModel = userProfileViewModel
                                )
                            }
                            composable(route = Routes.Main.Search.routes) {
                                SearchScreen(navHostController = navHostController)
                            }
                            composable(route = Routes.Main.MentorProfile.routes) {
                                MentorProfile(navHostController = navHostController)
                            }
                            composable(route = Routes.Main.CreateBooking.routes) {
                                CreateBookingScreen(navHostController = navHostController)
                            }

                            composable(route = Routes.Main.Profile.routes) {
                                ProfileScreen(navHostController = navHostController)
                            }
                            composable(route = Routes.Main.MentorDashboard.MentorForm.routes) {
                                ExpertFormScreen(navHostController = navHostController)
                            }
                            composable(route = Routes.Main.MentorDashboard.CreateSchedule.routes) {
                                CreateScheduleScreen(navHostController = navHostController)
                            }
                            composable(route = Routes.Main.ServiceProduct.ServiceProductList.routes) {
                                ServiceProductListScreen(navHostController = navHostController)
                            }

                        }
                    }
                }
            }
        }
    }
}
