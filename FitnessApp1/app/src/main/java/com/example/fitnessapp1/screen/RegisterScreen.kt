package com.example.fitnessapp1.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.ActivityLevelTextField
import com.example.fitnessapp1.components.DateTextField
import com.example.fitnessapp1.components.DividerText
import com.example.fitnessapp1.components.FloatTextField
import com.example.fitnessapp1.components.GenderTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.IntTextField
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.components.NormalClickableText
import com.example.fitnessapp1.components.NormalText
import com.example.fitnessapp1.components.NormalTextField
import com.example.fitnessapp1.components.PasswordTextField
import com.example.fitnessapp1.components.WeightGoalTextField
import com.example.fitnessapp1.shared.ActivityLevel
import com.example.fitnessapp1.shared.Gender
import com.example.fitnessapp1.shared.WeightGoal

@Composable
fun RegisterScreen(
    navController: NavHostController?,
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit,
    gender: Gender?,
    onGenderChange: (Gender) -> Unit,
    height: Float?,
    onHeightChange: (Float) -> Unit,
    weight: Float?,
    onWeightChange: (Float) -> Unit,
    activityLevel: ActivityLevel?,
    onActivityLevelChange: (ActivityLevel) -> Unit,
    weightGoal: WeightGoal?,
    onWeightGoalChange: (WeightGoal) -> Unit,
    goalSteps: Int?,
    onGoalStepsChange: (Int) -> Unit,
    onButtonClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
        ) {
            item {
                NormalText(value = stringResource(id = R.string.hello))
                HeadingText(
                    value = stringResource(id = R.string.create_account),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                NormalTextField(
                    value = username,
                    labelValue = stringResource(id = R.string.username),
                    onChange = onUsernameChange,
                    painterResource = Icons.Default.Person
                )

                PasswordTextField(
                    value = password,
                    labelValue = stringResource(id = R.string.password),
                    onChange = onPasswordChange,
                    painterResource = Icons.Default.Lock
                )

                DateTextField(
                    value = dateOfBirth,
                    onChange = onDateOfBirthChange,
                    painterResource = Icons.Default.CalendarMonth
                )

                Spacer(modifier = Modifier.padding(4.dp))

                GenderTextField(
                    value = gender,
                    onChange = onGenderChange,
                    painterResource = Icons.Default.Person,
                )

                FloatTextField(
                    value = height.toString(),
                    labelValue = stringResource(id = R.string.height),
                    onChange = { onHeightChange(it.toFloat()) },
                    painterResource = Icons.Default.Height
                )

                FloatTextField(
                    value = weight.toString(),
                    labelValue = stringResource(id = R.string.weight),
                    onChange = { onWeightChange(it.toFloat()) },
                    painterResource = Icons.Default.Scale
                )

                Spacer(modifier = Modifier.padding(4.dp))

                ActivityLevelTextField(
                    value = activityLevel,
                    onChange = onActivityLevelChange,
                    painterResource = Icons.Default.FitnessCenter
                )

                Spacer(modifier = Modifier.padding(4.dp))

                WeightGoalTextField(
                    value = weightGoal,
                    onChange = onWeightGoalChange,
                    painterResource = Icons.Default.Scale
                )

                IntTextField(
                    value = goalSteps.toString(),
                    labelValue = stringResource(id = R.string.goal_steps),
                    onChange = { onGoalStepsChange(it.toInt()) },
                    painterResource = Icons.AutoMirrored.Filled.DirectionsWalk
                )

                Spacer(modifier = Modifier.height(40.dp))

                NormalButton(
                    value = stringResource(id = R.string.register),
                    onClick = {
                        onButtonClick()
                        navController?.navigate("main")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerText(value = stringResource(id = R.string.or))

                NormalClickableText(
                    initialText = stringResource(id = R.string.go_to_login),
                    mainText = stringResource(id = R.string.login),
                    onTextSelected = {
                        navController?.navigate("login")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        null,
        "",
        {},
        "",
        {},
        "",
        {},
        Gender.MALE,
        {},
        0.0f,
        {},
        0f,
        {},
        ActivityLevel.SEDENTARY,
        {},
        WeightGoal.MAINTAIN,
        {},
        0,
        {},
        {},
    )
}
