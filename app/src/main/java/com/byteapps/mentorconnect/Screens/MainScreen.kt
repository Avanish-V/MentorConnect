package com.byteapps.mentorconnect.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.ExpertDrawerItemsList
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.Utils.drawerItemsList
import com.byteapps.mentorconnect.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {

    val navigationDrawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    DismissibleNavigationDrawer(
        drawerContent = {

            Column(modifier = Modifier
                .width(screenWidth.dp / 2)
                .fillMaxHeight()
                .background(color = AppTheme.colorScheme.background)){

                Column (modifier = Modifier
                    .clickable { navHostController.navigate(Routes.Main.Profile.routes) }
                    .padding(20.dp),verticalArrangement = Arrangement.spacedBy(24.dp)){
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.cat),
                        contentDescription = null
                    )
                    Column (verticalArrangement = Arrangement.spacedBy(10.dp)){
                        Text("Avanish Kumar", fontWeight = FontWeight.Bold)
                        Text("akv1042003@gmail.com", maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }

                }
                HorizontalDivider(
                    modifier = Modifier.width(IntrinsicSize.Min)
                )
                LazyColumn (contentPadding = PaddingValues(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){

                    items(drawerItemsList){
                        NavigationDrawerItem(
                            label = { Text(text = it.name) },
                            selected = false,
                            onClick = {

                                if (it.name == "Become Mentor"){
                                    navHostController.navigate(Routes.Main.MentorDashboard.MentorForm.routes)
                                }
                                if (it.name == "Schedule"){
                                    navHostController.navigate(Routes.Main.MentorDashboard.CreateSchedule.routes)
                                }
                                      },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(it.icon),
                                    contentDescription = "UserImage",
                                    tint = AppTheme.colorScheme.primary
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),

                        )
                    }

                    items(ExpertDrawerItemsList){

                        NavigationDrawerItem(
                            label = { Text(text = it.name) },
                            selected = false,
                            onClick = {

                                if (it.name == "Become Mentor"){
                                    navHostController.navigate(Routes.Main.MentorDashboard.MentorForm.routes)
                                }
                                if (it.name == "Schedule"){
                                    navHostController.navigate(Routes.Main.MentorDashboard.CreateSchedule.routes)
                                }
                            },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(it.icon),
                                    contentDescription = "UserImage",
                                    tint = AppTheme.colorScheme.primary
                                )
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            ),

                            )

                    }

                }
            }
        },
        drawerState = navigationDrawer
    ) {

        val greetString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White, fontWeight = FontWeight.Bold)){
                append("Hello, ")
            }

            append("Avanish!")

            toAnnotatedString()
        }

        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = greetString)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    )
                )
            },
            containerColor = Color.White

        ){paddingValues ->

            LazyColumn(modifier = Modifier.padding(paddingValues), verticalArrangement = Arrangement.spacedBy(32.dp)) {

                item {
                    HeroSection(
                        onSearchClick = {
                            navHostController.navigate(Routes.Main.Search.routes)
                        },
                        navHostController = navHostController
                    )
                }

                item {

                }

                item {


                    Box(modifier = Modifier.padding(start = 16.dp)){
                        Text(
                            text = "Top 10 Expert",
                            style = AppTheme.typography.titleLarge
                        )
                    }
                    LazyRow (contentPadding = PaddingValues(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)){
                        items(5){
                            TopMentorsCard(onCardClick = {
                                navHostController.navigate(Routes.Main.MentorProfile.routes)
                            })
                        }
                    }
                }

                item {

                    Column (
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ){

                        Text(text = "Why Choose Our Platform?", style = AppTheme.typography.titleLarge)

                        featureList.forEach {
                            FeatureSingleCard(
                                title = it.title,
                                description = it.description,
                                icon = it.icon
                            )
                        }
                    }
                }

                item {
                    Column (
                        modifier = Modifier.fillMaxWidth().background(
                            color = AppTheme.colorScheme.primary
                        ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp, horizontal = 40.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Ready to Accelerate Your Career?",
                                style = AppTheme.typography.titleLarge,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "Join thousands of professionals who have transformed their careers with our mentorship program",
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )

                            Button(
                              onClick = {},
                              shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = AppTheme.colorScheme.primary
                                )
                            ) {
                                Text("Be An Expert")

                            }
                        }


                    }
                }
            }
        }
    }
}

val featureList = listOf<FeatureItems>(
    FeatureItems(
        title = "Personalized Matching",
        description = "Our AI-powered algorithm finds the perfect mentor based on your goals, industry, and experience level.",
        icon = R.drawable.bullseye
    ),
    FeatureItems(
        title = "Flexible Scheduling",
        description = "Book sessions at your convenience with our easy-to-use scheduling system.",
        icon = R.drawable.calendar_clock
    ),
    FeatureItems(
        title = "Rich Resources",
        description = "Access exclusive resources, workshops, and learning materials from industry experts.",
        icon = R.drawable.book_open_cover
    ),

)
data class FeatureItems(
    val title:String,
    val description:String,
    val icon:Int
)

@Composable
fun FeatureSingleCard(
    title: String,
    description: String,
    icon: Int,
) {

    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = AppTheme.colorScheme.primary
                )
                Text(title, style = AppTheme.typography.titleMedium)
            }
            Text(text = description)
        }

    }


}

@Composable
fun TopMentorsCard(onCardClick:()->Unit) {
    Card(
        onClick = {
            onCardClick.invoke()
        },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(300.dp)
            .shadow(
                elevation = 0.dp,
                spotColor = AppTheme.colorScheme.primary,
                ambientColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Top Rated",
                color = Color(0xFFFFD700), // Yellow color
                fontSize = 12.sp,
                modifier = Modifier
                    .background(
                        color = Color(0xFFFFF9C4), // Light yellow background
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.cat),
                    contentDescription = "Mentor",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "John Smith", fontWeight = FontWeight.Bold)
                    Text(text = "Product Lead at Google", fontSize = 12.sp, color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconWithText(iconColor = Color(0xFFFFD700), iconText = "4.9")
                IconWithText(iconColor = Color.Gray, iconText = "200+ sessions")
                IconWithText(iconColor = Color(0xFF1E88E5), iconText = "Top 10")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Chip(text = "Product Management")
                    Chip(text = "Leadership")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Helping professionals transition into PM roles and grow their careers.",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun IconWithText(iconColor: Color, iconText: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = android.R.drawable.star_on),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = iconText, fontSize = 12.sp)
    }
}

@Composable
fun Chip(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = Color.Black,
        modifier = Modifier
            .background(color = AppTheme.colorScheme.background, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        textAlign = TextAlign.Center
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroSection(
    onSearchClick:()->Unit,
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            "Find Your Perfect Career Mentor",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Connect with industry experts who can guide you",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            onClick = {onSearchClick.invoke()},
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Row (
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.search),
                    contentDescription = "UserImage",
                )
                Text("Search Mentor...", color = Color.LightGray)
            }
        }
    }
}
