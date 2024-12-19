package com.byteapps.mentorconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.byteapps.mentorconnect.Screens.BeAnExpert.ExpertFormScreen
import com.byteapps.mentorconnect.Screens.CreateScheduleScreen
import com.byteapps.mentorconnect.Screens.MainScreen
import com.byteapps.mentorconnect.Screens.ProfileScreen
import com.byteapps.mentorconnect.Screens.Register.CreateProfileScreen
import com.byteapps.mentorconnect.Screens.Register.SignInScreen
import com.byteapps.mentorconnect.Screens.SearchFlow.CreateBookingScreen
import com.byteapps.mentorconnect.Screens.SearchFlow.MentorProfile
import com.byteapps.mentorconnect.Screens.SearchFlow.SearchScreen
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background
                ) {
                    NavHost(
                        modifier = Modifier.background(Color.White),
                        navController = navHostController,
                        startDestination = Routes.Register.routes
                    ) {

                        navigation(
                            startDestination = Routes.Register.SignIn.routes,
                            route = Routes.Register.routes
                        ){
                            composable(route = Routes.Register.SignIn.routes) {
                                SignInScreen(navHostController = navHostController)
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
                                MainScreen(navHostController = navHostController)
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
                        }
                    }
                }

            }
        }
    }
}
