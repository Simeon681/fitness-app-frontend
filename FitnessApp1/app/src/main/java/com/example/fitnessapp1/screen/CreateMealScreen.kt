@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fitnessapp1.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.IntTextField
import com.example.fitnessapp1.components.NormalElevatedButton
import com.example.fitnessapp1.components.NormalTextField
import com.example.fitnessapp1.state.MealState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateMealScreen(
    state: MealState,
    onStateChange: (MealState) -> Unit,
    mealName: String,
    onMealNameChange: (String) -> Unit,
    calories: Int,
    onCaloriesChange: (Int) -> Unit,
    protein: Float,
    onProteinChange: (Float) -> Unit,
    carbs: Float,
    onCarbsChange: (Float) -> Unit,
    fat: Float,
    onFatChange: (Float) -> Unit,
    onButtonClick: () -> Unit,
    goToMain: () -> Unit,
    onArrowClick: () -> Unit
) {
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    if (showError) {
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

        showError = false
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.DarkGray
                    ),
                    title = {
                        Text(text = stringResource(id = R.string.home))
                    },
                    navigationIcon = {
                        IconButton(onClick = { onArrowClick() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                        }
                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(start = 14.dp, top = 16.dp, end = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HeadingText(
                            value = stringResource(id = R.string.create_meal),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.padding(20.dp))

                        NormalTextField(
                            value = mealName,
                            labelValue = stringResource(id = R.string.meal_name),
                            onChange = onMealNameChange,
                            painterResource = Icons.Default.Fastfood
                        )

                        IntTextField(
                            value = calories.toString(),
                            labelValue = stringResource(id = R.string.calories),
                            onChange = { onCaloriesChange(it.toInt()) },
                            painterResource = Icons.Default.Fastfood
                        )

                        FloatTextField(
                            value = protein.toString(),
                            labelValue = stringResource(id = R.string.protein),
                            onChange = { onProteinChange(it.toFloat()) },
                            painterResource = Icons.Default.Fastfood
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        FloatTextField(
                            value = carbs.toString(),
                            labelValue = stringResource(id = R.string.carbs),
                            onChange = { onCarbsChange(it.toFloat()) },
                            painterResource = Icons.Default.Fastfood
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        FloatTextField(
                            value = fat.toString(),
                            labelValue = stringResource(id = R.string.fat),
                            onChange = { onFatChange(it.toFloat()) },
                            painterResource = Icons.Default.Fastfood
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        NormalElevatedButton(
                            text = stringResource(id = R.string.create_meal),
                            color = Color.Cyan,
                            onClick = {
                                if (mealName.isEmpty()) {
                                    errorMessage = "Please enter a meal name!"
                                    showError = true
                                } else if (mealName.length !in 2..31) {
                                    errorMessage = "Meal name must be between 2 and 32 characters long!"
                                    showError = true
                                } else if (calories == 0) {
                                    errorMessage = "Please enter a calorie amount!"
                                    showError = true
                                } else if (protein !in 1f..100f) {
                                    errorMessage = "Protein must be between 1 and 100 grams"
                                    showError = true
                                } else if (carbs !in 1f..100f) {
                                    errorMessage = "Carbs must be between 1 and 100 grams"
                                    showError = true
                                } else if (fat !in 1f..100f) {
                                    errorMessage = "Fat must be between 1 and 100 grams"
                                    showError = true
                                } else {
                                    onButtonClick()
                                }
                            }
                        )
                    }

                    if (state is MealState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    if (state is MealState.Error) {
                        Toast.makeText(
                            LocalContext.current,
                            state.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    if (state is MealState.Success) {
                        goToMain()
                        onStateChange(MealState.Empty)
                    }
                }
            }
        )
    }
}
