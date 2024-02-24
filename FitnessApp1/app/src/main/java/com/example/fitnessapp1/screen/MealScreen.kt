package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.MealTypeTextField
import com.example.fitnessapp1.resource.response.MealResponse
import com.example.fitnessapp1.shared.MealType

@Composable
fun MealScreen(
    navController: NavHostController?,
    onGetMeal: () -> Unit,
    meal: MealResponse?,
    portion: Float?,
    onPortionChange: (Float) -> Unit,
    type: MealType?,
    onMealTypeChange: (MealType) -> Unit,
    onAddMealToPlan: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onGetMeal()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, top = 16.dp, end = 14.dp),
        color = MaterialTheme.colorScheme.background,
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(
            state = lazyListState
        ) {
            item {
                HeadingText(
                    value = "${meal?.name}",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Calories: ${meal?.calories} kcal",
                    fontSize = 20.sp
                )
                Text(
                    text = "Protein: ${meal?.protein} g",
                    fontSize = 20.sp
                )
                Text(
                    text = "Carbohydrates: ${meal?.carbs} g",
                    fontSize = 20.sp
                )
                Text(
                    text = "Fat: ${meal?.fat} g",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.padding(16.dp))

                FloatTextField(
                    value = portion.toString(),
                    labelValue = "Portion (grams)",
                    onChange = { onPortionChange(it.toFloat()) },
                    painterResource = Icons.Default.Fastfood
                )

                Spacer(modifier = Modifier.padding(4.dp))

                MealTypeTextField(
                    value = type,
                    onChange = onMealTypeChange,
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
                            onAddMealToPlan()
                            navController?.navigate("main")
                        }
                    ) {
                        Text(
                            text = "Add meal to plan",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(
        navController = null,
        onGetMeal = {},
        meal = null,
        portion = null,
        onPortionChange = {},
        type = null,
        onMealTypeChange = {},
        onAddMealToPlan = {}
    )
}
