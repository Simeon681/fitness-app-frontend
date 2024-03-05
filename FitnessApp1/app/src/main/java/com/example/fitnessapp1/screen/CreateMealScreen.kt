package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.IntTextField
import com.example.fitnessapp1.components.NormalTextField

@Composable
fun CreateMealScreen(
    navController: NavHostController?,
    mealName: String,
    onMealNameChange: (String) -> Unit,
    calories: Int?,
    onCaloriesChange: (Int) -> Unit,
    protein: Float?,
    onProteinChange: (Float) -> Unit,
    carbs: Float?,
    onCarbsChange: (Float) -> Unit,
    fat: Float?,
    onFatChange: (Float) -> Unit,
    onButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, top = 16.dp, end = 14.dp),
        color = Color.White,
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
                labelValue = "Name",
                onChange = onMealNameChange,
                painterResource = Icons.Default.Fastfood
            )

            IntTextField(
                value = calories.toString(),
                labelValue = "Calories",
                onChange = { onCaloriesChange(it.toInt()) },
                painterResource = Icons.Default.Fastfood
            )

            FloatTextField(
                value = protein.toString(),
                labelValue = "Protein",
                onChange = { onProteinChange(it.toFloat()) },
                painterResource = Icons.Default.Fastfood
            )

            FloatTextField(
                value = carbs.toString(),
                labelValue = "Carbs",
                onChange = { onCarbsChange(it.toFloat()) },
                painterResource = Icons.Default.Fastfood
            )

            FloatTextField(
                value = fat.toString(),
                labelValue = "Fat",
                onChange = { onFatChange(it.toFloat()) },
                painterResource = Icons.Default.Fastfood
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedButton(
                    modifier = Modifier.size(200.dp, 50.dp),
                    onClick = {
                        onButtonClick()
                        navController?.navigate("main")
                    }
                ) {
                    Text(
                        text = "Create Meal",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateMealScreenPreview() {
    CreateMealScreen(
        navController = null,
        mealName = "Chicken",
        onMealNameChange = {},
        calories = 100,
        onCaloriesChange = {},
        protein = 20.0f,
        onProteinChange = {},
        carbs = 10.0f,
        onCarbsChange = {},
        fat = 5.0f,
        onFatChange = {},
        onButtonClick = {}
    )
}
