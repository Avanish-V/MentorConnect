package com.byteapps.mentorconnect.Screens.BeAnExpert

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.byteapps.mentorconnect.Network.UserProfile.data.Experience
import com.byteapps.mentorconnect.Network.UserProfile.data.MentorProfileDTO
import com.byteapps.mentorconnect.Network.UserProfile.presentation.UserProfileViewModel
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Screens.ServiceProducts.CustomTextField
import com.byteapps.mentorconnect.Utils.ResultState
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpertFormScreen(navHostController: NavHostController) {

    val expertFormSharedViewModel = koinInject<ExpertFormSharedViewModel>()
    val userProfileViewModel = koinInject<UserProfileViewModel>()
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Be An Expert") },
                navigationIcon = {
                    IconButton(onClick = {navHostController.navigate(Routes.Main.Dashboard.routes)}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colorScheme.background,
                    navigationIconContentColor = AppTheme.colorScheme.primary,
                    titleContentColor = AppTheme.colorScheme.onTertiary
                )
            )
        },
        containerColor = AppTheme.colorScheme.background
    ) {

        var currentStep by remember { mutableIntStateOf(0) }
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(16.dp)
        ) {

            StepProgressIndicator(
                currentStep = currentStep,
                totalSteps = 2
            )

            when (currentStep) {

                0 -> ExperienceSection(
                    expertFormSharedViewModel =expertFormSharedViewModel,
                    onNext = { currentStep++ },
                    onBack = { currentStep-- }
                )
                1 -> ExpertiseSection(
                    expertFormSharedViewModel = expertFormSharedViewModel,
                    onSubmit = {

                        scope.launch {
                            userProfileViewModel.createMentorProfile(
                                MentorProfileDTO(
                                    experience = listOf(
                                        Experience(
                                            currentRole = expertFormSharedViewModel.currentRole.value,
                                            company = expertFormSharedViewModel.company.value,
                                            experience = expertFormSharedViewModel.experience.value,
                                            description = expertFormSharedViewModel.description.value
                                        )
                                    ),
                                    expertise = expertFormSharedViewModel.expertise.value
                                )
                            )
                                .collect{
                                    when(it){
                                        is ResultState.Loading->{

                                        }
                                        is ResultState.Success->{
                                            userProfileViewModel.getUserProfile()
                                        }
                                        is ResultState.Error->{

                                        }
                                    }
                                }
                        }


                    },
                    onBack = { currentStep-- }
                )

            }
        }
    }
}


@Composable
fun BasicInfoSection(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Basic Information",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colorScheme.onTertiary
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {},
            label = "Full Name",
            placeHolder = "Your Name",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {},
            label = "Email",
            placeHolder = "@gmail.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colorScheme.primary
            )
        ) {
            Text("Next")
        }
    }
}

@Composable
fun SocialAccounts(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Social Accounts",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colorScheme.onTertiary
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "https://"
                )
            },
            leadingIcon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.linkedin),
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = AppTheme.colorScheme.primary
            ),
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "https://",
                )
            },
            leadingIcon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.instagram),
                    contentDescription = null
                )
            }
        )


        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppTheme.colorScheme.primary
            )
        ) {
            Text("Next")
        }
    }
}


@Composable
fun ExperienceSection(expertFormSharedViewModel: ExpertFormSharedViewModel,onNext: () -> Unit, onBack: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Professional Details",
            style = MaterialTheme.typography.titleLarge,
            color = AppTheme.colorScheme.onTertiary
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {expertFormSharedViewModel.setCurrentRole(it.toString())},
            label = "Current Role",
            placeHolder = "Ex-Software Engineer",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {expertFormSharedViewModel.setCompany(it.toString())},
            label = "Company",
            placeHolder = "Ex-Google",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {expertFormSharedViewModel.setExperience(it.toString())},
            label = "Experience",
            placeHolder = "Year",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            value = {expertFormSharedViewModel.setDescription(it.toString())},
            label = "About",
            placeHolder = "Job description",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = onNext,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.primary
                )
            ) {
                Text("Next")
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpertiseSection(
    expertFormSharedViewModel: ExpertFormSharedViewModel,
    onSubmit: () -> Unit,
    onBack: () -> Unit
) {

    var expertise by remember { mutableStateOf("") }
    val expertiseList = expertFormSharedViewModel.expertise.collectAsState().value

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {


            Text(
                text = "Expertise",
                style = MaterialTheme.typography.titleLarge,
                color = AppTheme.colorScheme.onTertiary
            )

            FlowRow (
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                expertiseList.forEach {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(it, modifier = Modifier.padding(10.dp), color = AppTheme.colorScheme.onTertiary)
                        },
                        trailingIcon = {

                            IconButton(onClick = {expertFormSharedViewModel.deleteExpertise(it)}) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )
                            }
                        },
                        border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    )
                }
            }


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = expertise,
                onValueChange = {
                   expertise = it
                },
                placeholder = {
                    Text(
                        text = "Expertise",
                        color = AppTheme.colorScheme.onSecondary
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = AppTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.LightGray,
                ),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(
                    color = AppTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight.Bold
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        expertFormSharedViewModel.setExpertise(
                            expertise
                        )
                        expertise = ""
                    }
                )
            )


        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = AppTheme.colorScheme.onSecondary
                ),
                colors = ButtonDefaults.buttonColors(
                    contentColor = AppTheme.colorScheme.onSecondary,
                    containerColor = Color.Transparent
                )
            ) {
                Text("Back")
            }
            Button(
                onClick = {onSubmit.invoke()

                          },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.primary
                )
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun StepProgressIndicator(currentStep: Int, totalSteps: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(totalSteps) { step ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(
                        color = if (step <= currentStep)
                            AppTheme.colorScheme.primary
                        else
                           Color.LightGray,
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}