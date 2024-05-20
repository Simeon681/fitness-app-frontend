package com.example.fitnessapp1.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp1.R
import com.example.fitnessapp1.SharedPreferencesInstance
import com.example.fitnessapp1.components.ActivityCard
import com.example.fitnessapp1.components.CaloriesCard
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.HeightLineChartCard
import com.example.fitnessapp1.components.NormalElevatedButton
import com.example.fitnessapp1.components.WeightLineChartCard
import com.example.fitnessapp1.resource.response.ActivityStatResponse
import com.example.fitnessapp1.resource.response.HeightChangeResponse
import com.example.fitnessapp1.resource.response.ProfileResponse
import com.example.fitnessapp1.resource.response.WeightChangeResponse
import com.example.fitnessapp1.state.ActivityStatState
import com.example.fitnessapp1.state.HeightChangeState
import com.example.fitnessapp1.state.LoginState
import com.example.fitnessapp1.state.ProfileState
import com.example.fitnessapp1.state.WeightChangeState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MainScreen(
    profileState: ProfileState,
    onProfileStateChange: (ProfileState) -> Unit,
    activityStatState: ActivityStatState,
    onActivityStatStateChange: (ActivityStatState) -> Unit,
    heightChangesState: HeightChangeState,
    onHeightChangesStateChange: (HeightChangeState) -> Unit,
    weightChangesState: WeightChangeState,
    onWeightChangesStateChange: (WeightChangeState) -> Unit,
    logoutState: LoginState,
    onLogoutStateChange: (LoginState) -> Unit,
    onGetProfile: () -> Unit,
    onGetActivityStat: () -> Unit,
    profile: ProfileResponse?,
    activityStat: ActivityStatResponse,
    steps: Int,
    heightChanges: List<HeightChangeResponse>?,
    onGetHeightChanges: () -> Unit,
    weightChanges: List<WeightChangeResponse>?,
    onLogoutClick: () -> Unit,
    onGetWeightChanges: () -> Unit,
    onStepsChange: (Int) -> Unit,
    onWaterChange: (Float) -> Unit,
    updateActivityStat: () -> Unit,
    onClickUpdateWeight: () -> Unit,
    onClickUpdateHeight: () -> Unit,
    onAddMealClick: () -> Unit,
    onCreateMealClick: () -> Unit,
    goToWelcomeScreen: () -> Unit
) {
    val sharedPreferencesInstance = SharedPreferencesInstance

    LaunchedEffect(key1 = true) {
        onGetProfile()
        onGetActivityStat()
        onStepsChange(sharedPreferencesInstance.getSteps())
        onGetHeightChanges()
        onGetWeightChanges()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        val lazyListState = rememberLazyListState()
        val lazyRowState = rememberLazyListState()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .padding(top = 16.dp),
                        state = lazyListState
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 14.dp, end = 14.dp)
                            ) {
                                HeadingText(
                                    value = stringResource(id = R.string.today),
                                    textAlign = TextAlign.Start,
                                    button = true,
                                    onButtonClick = {
                                        onLogoutClick()
                                    }
                                )

                                Text(
                                    text = LocalDate.now()
                                        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                                    color = Color.Gray,
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                ActivityCard(
                                    steps = steps,
                                    profile = profile,
                                    activityStat = activityStat,
                                    onWaterChange = onWaterChange,
                                    updateActivityStat = updateActivityStat
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                CaloriesCard(profile, activityStat)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            LazyRow(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 14.dp, end = 14.dp),
                                state = lazyRowState
                            ) {
                                item {
                                    if (heightChanges != null) {
                                        HeightLineChartCard(
                                            heightChanges,
                                            onClickUpdateHeight
                                        )
                                    }

                                    Spacer(modifier = Modifier.padding(14.dp))


                                    if (weightChanges != null) {
                                        WeightLineChartCard(
                                            weightChanges,
                                            onClickUpdateWeight
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                NormalElevatedButton(
                                    text = "Add Meal",
                                    color = Color.Green,
                                    onClick = onAddMealClick
                                )

                                Spacer(modifier = Modifier.height(16.dp))


                                NormalElevatedButton(
                                    text = stringResource(id = R.string.create_meal),
                                    color = Color.Cyan,
                                    onClick = onCreateMealClick
                                )

                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    }

                    if (profileState is ProfileState.Loading ||
                        activityStatState is ActivityStatState.Loading ||
                        heightChangesState is HeightChangeState.Loading ||
                        weightChangesState is WeightChangeState.Loading ||
                        logoutState is LoginState.Loading
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }

                    if (logoutState is LoginState.Error) {
                        Toast.makeText(
                            LocalContext.current,
                            logoutState.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    if (profileState is ProfileState.Success) {
                        onProfileStateChange(ProfileState.Empty)
                    }

                    if (activityStatState is ActivityStatState.Success) {
                        onActivityStatStateChange(ActivityStatState.Empty)
                    }

                    if (heightChangesState is HeightChangeState.Success) {
                        onHeightChangesStateChange(HeightChangeState.Empty)
                    }

                    if (weightChangesState is WeightChangeState.Success) {
                        onWeightChangesStateChange(WeightChangeState.Empty)
                    }

                    if (logoutState is LoginState.Success) {
                        goToWelcomeScreen()
                        onLogoutStateChange(LoginState.Empty)
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        profileState = ProfileState.Empty,
        onProfileStateChange = {},
        activityStatState = ActivityStatState.Empty,
        onActivityStatStateChange = {},
        heightChangesState = HeightChangeState.Empty,
        onHeightChangesStateChange = {},
        weightChangesState = WeightChangeState.Empty,
        onWeightChangesStateChange = {},
        logoutState = LoginState.Empty,
        onLogoutStateChange = {},
        onGetProfile = {},
        onGetActivityStat = {},
        profile = null,
        activityStat = ActivityStatResponse("", 0, 0, 0f, 0f, 0f, 0f),
        steps = 0,
        heightChanges = null,
        onGetHeightChanges = {},
        weightChanges = null,
        onLogoutClick = {},
        onGetWeightChanges = {},
        onStepsChange = {},
        onWaterChange = {},
        updateActivityStat = {},
        onClickUpdateWeight = {},
        onClickUpdateHeight = {},
        onAddMealClick = {},
        onCreateMealClick = {},
        goToWelcomeScreen = {}
    )
}