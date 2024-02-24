package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.ActivityCard
import com.example.fitnessapp1.components.CaloriesCard
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.shared.MealType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen(
    navController: NavHostController?,
    onGetProfile: () -> Unit,
    onGetActivityStat: () -> Unit,
    profile: ProfileResponse?,
    activityStat: ActivityStatResponse,
    steps: Int?,
    onStepsChange: (Int) -> Unit,
    onWaterChange: (Float) -> Unit,
    updateActivityStat: () -> Unit,
    mealId: Long?,
    onMealIdChange: (Long) -> Unit,
    type: MealType?,
    onMealTypeChange: (MealType) -> Unit,
    onMealStatButtonClick: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onGetProfile()
        onGetActivityStat()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
        ) { innerPadding ->
            val lazyListState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 14.dp, top = 16.dp, end = 14.dp),
                state = lazyListState
            ) {
                item {

                    HeadingText(
                        value = stringResource(id = R.string.today),
                        textAlign = null
                    )

                    Text(
                        text = LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        color = Color.Gray,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ActivityCard(
                        profile = profile,
                        activityStat = activityStat,
                        onWaterChange = onWaterChange,
                        updateActivityStat = updateActivityStat
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    CaloriesCard(profile, activityStat)

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ElevatedButton(
                            modifier = Modifier.size(200.dp, 50.dp),
                            onClick = { navController?.navigate("search") }
                        ) {
                            Text(
                                text = "Add Meal",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = null,
        onGetProfile = {},
        onGetActivityStat = {},
        profile = ProfileResponse(
            1,
            0f,
            25f,
            180f,
            80.0f,
            0,
        ),
        activityStat = ActivityStatResponse(
            1,
            0,
            0,
            0f,
            0f,
            0f,
            0f
        ),
        steps = 0,
        onStepsChange = {},
        onWaterChange = {},
        updateActivityStat = {},
        mealId = null,
        onMealIdChange = {},
        type = null,
        onMealTypeChange = {},
        onMealStatButtonClick = {}
    )
}