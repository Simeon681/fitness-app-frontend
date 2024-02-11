package com.example.fitnessapp1.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnessapp1.R
import com.example.fitnessapp1.components.DateTextField
import com.example.fitnessapp1.components.DividerText
import com.example.fitnessapp1.components.EnumTextField
import com.example.fitnessapp1.components.HeadingText
import com.example.fitnessapp1.components.NormalButton
import com.example.fitnessapp1.components.NormalClickableText
import com.example.fitnessapp1.components.NormalText
import com.example.fitnessapp1.components.NormalTextField
import com.example.fitnessapp1.components.PasswordTextField
import com.example.fitnessapp1.view_model.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(
    navController: NavHostController?,
    registerViewModel: RegisterViewModel = koinViewModel()
) {
    val username by registerViewModel.username.collectAsState()
    val password by registerViewModel.password.collectAsState()
    val dateOfBirth by registerViewModel.dateOfBirth.collectAsState()
    val gender by registerViewModel.gender.collectAsState()
    val height by registerViewModel.height.collectAsState()
    val weight by registerViewModel.weight.collectAsState()
    val goalCalories by registerViewModel.goalCalories.collectAsState()
    val goalWeight by registerViewModel.goalWeight.collectAsState()
    val goalSteps by registerViewModel.goalSteps.collectAsState()
    val goalWater by registerViewModel.goalWater.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalText(value = stringResource(id = R.string.hello))
            HeadingText(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            NormalTextField(
                labelValue = stringResource(id = R.string.username),
                onChange = {
                    registerViewModel.saveUsername(it)
                },
                keyboardType = KeyboardType.Text,
                painterResource = Icons.Default.Person
            )
            
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                onChange = {
                    registerViewModel.savePassword(it)
                },
                painterResource = Icons.Default.Lock
            )

            DateTextField(
                labelValue = stringResource(id = R.string.date_of_birth),
                onChange = {
                    registerViewModel.saveDateOfBirth(it)
                },
                painterResource = Icons.Default.CalendarMonth
            )

            EnumTextField(
                onChange = {
                    registerViewModel.saveGender(it)
                },
                painterResource = Icons.Default.Person,
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.height),
                onChange = {
                    registerViewModel.saveHeight(it.toFloat())
                },
                keyboardType = KeyboardType.Decimal,
                painterResource = Icons.Default.Height
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.weight),
                onChange = {
                    registerViewModel.saveWeight(it.toFloat())
                },
                keyboardType = KeyboardType.Decimal,
                painterResource = Icons.Default.Scale
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.goal_calories),
                onChange = {
                    registerViewModel.saveGoalCalories(it.toInt())
                },
                keyboardType = KeyboardType.Number,
                painterResource = Icons.Default.Fastfood
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.goal_weight),
                onChange = {
                    registerViewModel.saveGoalWeight(it.toFloat())
                },
                keyboardType = KeyboardType.Decimal,
                painterResource = Icons.Default.Scale
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.goal_steps),
                onChange = {
                    registerViewModel.saveGoalSteps(it.toInt())
                },
                keyboardType = KeyboardType.Number,
                painterResource = Icons.AutoMirrored.Filled.DirectionsWalk
            )

            NormalTextField(
                labelValue = stringResource(id = R.string.goal_water),
                onChange = {
                    registerViewModel.saveGoalWater(it.toFloat())
                },
                keyboardType = KeyboardType.Decimal,
                painterResource = Icons.Default.WaterDrop
            )

            Spacer(modifier = Modifier.height(100.dp))

            NormalButton(
                value = stringResource(id = R.string.register),
                onClick = {
                    registerViewModel.register(
                        username,
                        password,
                        dateOfBirth,
                        gender,
                        height,
                        weight,
                        goalCalories,
                        goalWeight,
                        goalSteps,
                        goalWater
                    )
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(null)
}
