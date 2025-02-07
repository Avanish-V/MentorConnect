package com.byteapps.mentorconnect.Screens.ServiceProducts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.byteapps.mentorconnect.R
import com.byteapps.mentorconnect.Utils.Routes
import com.byteapps.mentorconnect.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceProductListScreen(navHostController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mentor Profile") },
                navigationIcon = {
                    IconButton(
                        onClick = { /* Handle navigation */ },
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = AppTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colorScheme.background,
                    titleContentColor = AppTheme.colorScheme.onTertiary,
                )
            )
        },
        containerColor = AppTheme.colorScheme.background
    ) { paddingValues ->

        Column (modifier = Modifier.padding(paddingValues)){

            LazyColumn (
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){

                item {
                    CategorySelection()
                }

                item {

                    Column (
                        modifier = Modifier.padding(13.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ){

                        CustomTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = {

                            },
                            label = "Service Title",
                            placeHolder = "Ex-Career guidance",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        CustomTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = {

                            },
                            label = "Description",
                            placeHolder = "Ex-About the service",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Row (modifier = Modifier.weight(1f)){
                                CustomTextField(
                                    modifier = Modifier,
                                    value = {
                                        // Handle value
                                    },
                                    label = "Duration",
                                    placeHolder = "Ex-1 Hour",
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )
                            }

                            Row (modifier = Modifier.weight(1f)){

                                CustomTextField(
                                    modifier = Modifier,
                                    value = {
                                        // Handle value
                                    },
                                    label = "Price",
                                    placeHolder = "Enter Price",
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )
                            }


                        }

                    }

                }


            }


            Button(
                onClick = {navHostController.navigate(Routes.Main.routes)},
                modifier = Modifier.padding(20.dp).fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colorScheme.primary
                )
            ) {
                Text("Create")
            }

        }

    }
}

val serviceCategoryList = listOf(
    "1:1 Meeting",
    "Digital Product"
)

@Composable
fun CategorySelection(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = "Category",
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colorScheme.onTertiary
        )
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ){

            items(serviceCategoryList){
                Box (modifier = Modifier.border(
                    width = 2.dp,
                    color = AppTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(10.dp)
                )){
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = it,
                        color = AppTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }


}

@Composable
fun CustomTextField(
    modifier: Modifier,
    value:(Any)->Unit,
    label:String,
    placeHolder:String,
    keyboardOptions: KeyboardOptions =  KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done // Ensure "Done" action is set
    ),
    keyboardActions: KeyboardActions = KeyboardActions(
        onDone = {
             // Hide the keyboard after submission
        }
    )
) {

    var fieldValue by remember {
        mutableStateOf("")
    }

    Column(

        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = label,
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colorScheme.onTertiary
        )
        OutlinedTextField(
            modifier = modifier,
            value = fieldValue,
            onValueChange = {
                value(it)
                fieldValue = it
            },
            placeholder = {
               Text(
                   text = placeHolder,
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
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }




}



