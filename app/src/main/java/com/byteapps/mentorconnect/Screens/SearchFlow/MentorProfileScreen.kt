package com.byteapps.mentorconnect.Screens.SearchFlow

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.byteapps.mentorconnect.Features.CreateSchedule.TimeSlotModel
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme

@SuppressLint("NewApi", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MentorProfile(navHostController: NavHostController) {

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("OverView","Services","Testimonials")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book a session", color = AppTheme.colorScheme.onTertiary) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = AppTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )

            )
        },
        containerColor = AppTheme.colorScheme.background
    ) { it ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = AppTheme.colorScheme.background
        ) {



            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                item {

                    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Card(
                                    Modifier.size(100.dp),
                                    shape = CircleShape,
                                    border = BorderStroke(
                                        width = 2.dp,
                                        color = AppTheme.colorScheme.primary
                                    ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.Transparent
                                    )
                                ) {
                                    Box(modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                        .padding(6.dp)) {
                                        Image(
                                            painter = painterResource(R.drawable.cat),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }


                                Column(modifier = Modifier.weight(1f)) {

                                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){

                                        Box(contentAlignment = Alignment.Center, modifier = Modifier) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Star,
                                                    contentDescription = null,
                                                    tint = AppTheme.colorScheme.secondary
                                                )
                                                Text(
                                                    "4.2/5",
                                                    color = AppTheme.colorScheme.onTertiary
                                                )
                                            }

                                        }

                                        Column(
                                            modifier = Modifier,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {

                                            Text(
                                                text = "20",
                                                fontWeight = FontWeight.Bold,
                                                color = AppTheme.colorScheme.onTertiary
                                            )

                                            Text(
                                                modifier = Modifier.padding(6.dp),
                                                text = "Sessions",
                                                color = AppTheme.colorScheme.onTertiary

                                            )
                                        }
                                    }
                                }
                            }

                            Column {
                                Text(
                                    text = "Avanish Kumar",
                                    fontWeight = FontWeight.Bold,
                                    style = AppTheme.typography.titleLarge,
                                    color = AppTheme.colorScheme.onTertiary
                                )
                            }

                        }
                    }
                }

                stickyHeader {

                    PrimaryTabRow(
                        selectedTabIndex = state,
                        containerColor = AppTheme.colorScheme.background,
                        indicator = {
                            TabRowDefaults.PrimaryIndicator(
                                modifier = Modifier. tabIndicatorOffset(state, matchContentSize = true),
                                color = AppTheme.colorScheme.primary,
                                width = 50.dp

                            )
                        }
                    ) {
                        titles.forEachIndexed { index, title ->
                            Tab(
                                selected = state == index,
                                onClick = { state = index },
                                text = {
                                    Text(
                                        text = title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,

                                    )
                                },
                                selectedContentColor = AppTheme.colorScheme.onTertiary,
                                unselectedContentColor = AppTheme.colorScheme.onSecondary
                            )
                        }
                    }
                }

                item {

                  
                    val pagerState = rememberPagerState(initialPage = state, pageCount = {titles.size})

                    state = pagerState.currentPage

                    HorizontalPager(
                        state = pagerState,
                        pageSize = PageSize.Fill,
                    ) {page->

                        when(page){
                            0->{
                                AboutSection()
                            }
                            1->{
                                ServiceProductSection(navHostController)
                            }
                            2->{
                                TestimonialCard()
                            }
                        }
                        
                    }
                    
                }


            }
        }
    }
}

@Composable
fun AboutSection(modifier: Modifier = Modifier) {


    Column (verticalArrangement = Arrangement.spacedBy(16.dp)){

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Card(
                onClick = {},
                modifier = Modifier.size(42.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(R.drawable.linkedin),
                        contentDescription = null
                    )
                }
            }

            Card(
                onClick = {},
                modifier = Modifier.size(42.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(R.drawable.instagram),
                        contentDescription = null
                    )
                }

            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Column(Modifier.weight(1f)) {
                    Text(
                        text = "About",
                        fontWeight = FontWeight.Bold,
                        color = AppTheme.colorScheme.onTertiary
                    )
                    HorizontalDivider(
                        modifier = Modifier.width(30.dp),
                        thickness = 3.dp,
                        color = AppTheme.colorScheme.primary
                    )
                }


            }
            Text(
                "Building @Tayyari | Ex-Microsoft | 400k+ Subs on YT |\n" +
                        "NSIT",
                color = AppTheme.colorScheme.onTertiary
            )
        }

    }



}

@Composable
fun ServiceProductSection(navHostController: NavHostController) {


    Column (verticalArrangement = Arrangement.spacedBy(16.dp)){

        LazyRow(
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            items(5) {
                SuggestionChip(
                    onClick = {},
                    label = { Text("Resume", modifier = Modifier.padding(10.dp), color = AppTheme.colorScheme.onTertiary) },
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                )
            }
        }

        for (i in 1..10){
            MentorshipCard(
                onBookClick = { navHostController.navigate(Routes.Main.CreateBooking.routes) }
            )

        }

    }


}


@Composable
fun TestimonialsSection(modifier: Modifier = Modifier) {

    for (i in 1..10){
        TestimonialCard()
    }


}


@Composable
fun TestimonialCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Profile Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://via.placeholder.com/48",
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Sarah Johnson",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Senior Software Engineer",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Rating
            Row {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Rating",
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Testimonial Text
            Text(
                text = "\"Working with my mentor has been transformative. Their guidance helped me advance my career and develop crucial leadership skills. The personalized approach made all the difference.\"",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Session Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "12 sessions completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Mentoring since 2023",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class SelectorModel(
    val isActive: Boolean,
    val timeSlotId: String,
    val selectorId: Int
)

@Composable
fun SingleScheduleDay(
    slotList: List<TimeSlotModel>? = null,
    selectedDate: String,
    isChecked: (Boolean) -> Unit,
    onAddSessionClick: () -> Unit,
    onDeleteTimeSlotClick: (timeSlotId: String) -> Unit,
    onTimeSetClick: (SelectorModel) -> Unit
) {

    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = AppTheme.colorScheme.secondary,
                shape = AppTheme.shape.container
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {
                        isChecked(it)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppTheme.colorScheme.primary
                    )
                )

                Text(text = selectedDate)
            }

            Row {

                IconButton(onClick = { onAddSessionClick.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = AppTheme.colorScheme.primary
                    )
                }
            }


        }


        slotList?.forEach { data ->

            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SingleTimeSector(
                        onClick = {
                            onTimeSetClick(
                                SelectorModel(
                                    isActive = true,
                                    timeSlotId = data.timeSlotId,
                                    selectorId = 1
                                )
                            )
                        },
                        time = "${data.start}"
                    )

                    HorizontalDivider(
                        Modifier
                            .padding(horizontal = 10.dp)
                            .width(10.dp),

                        thickness = 2.dp
                    )
                    SingleTimeSector(
                        onClick = {
                            onTimeSetClick(
                                SelectorModel(
                                    isActive = true,
                                    timeSlotId = data.timeSlotId,
                                    selectorId = 2
                                )
                            )
                        },
                        time = "${data.end}"
                    )

                }

                IconButton(onClick = { onDeleteTimeSlotClick.invoke(data.timeSlotId) }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Composable
fun SingleTimeSector(time: String, onClick: () -> Unit) {
    Card(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .width(96.dp)
            .height(48.dp),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Gray
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(time.ifEmpty { "--:--" })
        }
    }
}


@Composable
fun MentorshipCard(
    modifier: Modifier = Modifier,
    onBookClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Career guidance",
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colorScheme.onTertiary
            )
            Text(
                text = "Get personalized guidance on career transitions, resume review, and interview preparation.",
                style = MaterialTheme.typography.bodyMedium,
                color = AppTheme.colorScheme.onTertiary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ServiceDetail(
                    icon = R.drawable.clock_three__2_,
                    text = "30 mins"
                )
                ServiceDetail(
                    icon = R.drawable.camera_movie,
                    text = "Google Meet"
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "₹1,499",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = AppTheme.colorScheme.onTertiary
                    )
                    Text(
                        text = "/ session",
                        style = MaterialTheme.typography.bodySmall,
                        color = AppTheme.colorScheme.onTertiary,
                        modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
                    )
                }

                Button(
                    onClick = { onBookClick.invoke() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colorScheme.primary
                    )
                ) {
                    Text("Book Now")
                }

            }
        }
    }
}

@Composable
private fun ServiceDetail(
    icon: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint =  AppTheme.colorScheme.onTertiary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = AppTheme.colorScheme.onTertiary
        )
    }
}


@Composable
fun MentorSocialSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Connect With Me",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SocialButton(
                icon = Icons.Default.Add,
                platform = "LinkedIn",
                onClick = { /* Handle LinkedIn */ }
            )
            SocialButton(
                icon = Icons.Default.Person,
                platform = "Twitter",
                onClick = { /* Handle Twitter */ }
            )
            SocialButton(
                icon = Icons.Default.Search,
                platform = "YouTube",
                onClick = { /* Handle YouTube */ }
            )
        }

        // Stats Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialStat(count = "10K+", label = "Followers")
            SocialStat(count = "500+", label = "Posts")
            SocialStat(count = "50+", label = "Videos")
        }
    }
}

@Composable
private fun SocialButton(
    icon: ImageVector,
    platform: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = platform,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun SocialStat(
    count: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
