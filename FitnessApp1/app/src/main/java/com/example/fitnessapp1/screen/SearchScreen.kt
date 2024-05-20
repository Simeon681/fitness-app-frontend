package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.components.MealItems
import com.example.fitnessapp1.components.NormalSearchBar
import com.example.fitnessapp1.resource.response.MealResponse

@Composable
fun SearchScreen(
    navController: NavHostController?,
    mealName: String,
    onMealNameChange: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onSearchButtonClick: () -> Unit,
    meals: List<MealResponse>?,
    onMealIdChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
    ) {
        val lazyListState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .padding(start = 14.dp, top = 16.dp, end = 14.dp),
            state = lazyListState
        ) {
            item {
                NormalSearchBar(
                    query = mealName,
                    onQueryChange = onMealNameChange,
                    active = active,
                    onActiveChange = onActiveChange,
                    onSearchButtonClick = onSearchButtonClick
                )

                MealItems(
                    active = active,
                    meals = meals,
                    onMealIdChange = onMealIdChange,
                    onClick = { mealId ->
                        navController?.navigate("meal/$mealId")
                    }
                )
            }
        }
    }
}
