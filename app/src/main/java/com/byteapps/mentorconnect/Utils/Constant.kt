package com.byteapps.mentorconnect.Utils

import androidx.compose.ui.graphics.vector.ImageVector
import com.byteapps.mentorconnect.R

sealed class Routes(val routes:String){

    data object Register : Routes("REGISTER"){
        data object SignIn : Routes("SIGNIN")
        data object CreateProfile : Routes("CREATE_PROFILE")
    }

    data object Main : Routes("MAIN"){

        data object Dashboard : Routes("DASHBOARD")
        data object Search : Routes("SEARCH")
        data object MentorProfile : Routes("MENTOR_PROFILE")
        data object CreateBooking : Routes("CREATE_BOOKING")
        data object Bookings : Routes("BOOKINGS")
        data object Profile : Routes("Profile")


        data object MentorDashboard : Routes("MENTOR_DASHBOARD"){

            data object MentorForm : Routes("MENTOR_FORM")
            data object BookedSessions : Routes("BOOKED_SESSIONS")
            data object CreateSchedule : Routes("CREATE_SCHEDULE")

        }


    }



    data object Bookings : Routes("BOOKINGS")




}




data class NavigationDrawerRoute<T : Any>(val name: String, val route: T, val icon: Int)

val drawerItemsList = listOf(

    NavigationDrawerRoute(
        name = "Sessions",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.calendar_day
    ),
    NavigationDrawerRoute(
        name = "My Mentors",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.users
    ),
    NavigationDrawerRoute(
        name = "Become Mentor",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.badge__1_
    )

)

val ExpertDrawerItemsList = listOf(

    NavigationDrawerRoute(
        name = "Bookings",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.booking
    ),
    NavigationDrawerRoute(
        name = "Schedule",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.time_watch_calendar
    ),
    NavigationDrawerRoute(
        name = "Products",
        route = Routes.Main.Bookings.routes,
        icon = R.drawable.box_open
    )
)