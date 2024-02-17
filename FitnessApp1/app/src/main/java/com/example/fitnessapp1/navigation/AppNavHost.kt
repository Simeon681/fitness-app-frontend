package com.example.fitnessapp1.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessapp1.screen.ForgotPasswordScreen
import com.example.fitnessapp1.screen.LoginScreen
import com.example.fitnessapp1.screen.MainScreen
import com.example.fitnessapp1.screen.RegisterScreen
import com.example.fitnessapp1.view_model.LoginViewModel
import com.example.fitnessapp1.view_model.RegisterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Register.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.Login.route) {
            val viewModel = getViewModel<LoginViewModel>()
            val username by viewModel.username.collectAsState()
            val password by viewModel.password.collectAsState()
            LoginScreen(
                navController,
                username = username,
                onUsernameChange = { viewModel.saveUsername(it) },
                password = password,
                onPasswordChange = { viewModel.savePassword(it) },
                onButtonClick = { viewModel.login(username, password) }
            )
        }
        composable(NavigationItem.Register.route) {
            val viewModel = getViewModel<RegisterViewModel>()
            val username by viewModel.username.collectAsState()
            val password by viewModel.password.collectAsState()
            val dateOfBirth by viewModel.dateOfBirth.collectAsState()
            val gender by viewModel.gender.collectAsState()
            val height by viewModel.height.collectAsState()
            val weight by viewModel.weight.collectAsState()
            val activityLevel by viewModel.activityLevel.collectAsState()
            val weightGoal by viewModel.weightGoal.collectAsState()
            val goalSteps by viewModel.goalSteps.collectAsState()
            RegisterScreen(
                navController,
                username = username,
                onUsernameChange = { viewModel.saveUsername(it) },
                password = password,
                onPasswordChange = { viewModel.savePassword(it) },
                dateOfBirth = dateOfBirth,
                onDateOfBirthChange = { viewModel.saveDateOfBirth(it) },
                gender = gender,
                onGenderChange = { viewModel.saveGender(it) },
                height = height,
                onHeightChange = { viewModel.saveHeight(it) },
                weight = weight,
                onWeightChange = { viewModel.saveWeight(it) },
                activityLevel = activityLevel,
                onActivityLevelChange = { viewModel.saveActivityLevel(it) },
                weightGoal = weightGoal,
                onWeightGoalChange = { viewModel.saveWeightGoal(it) },
                goalSteps = goalSteps,
                onGoalStepsChange = { viewModel.saveGoalSteps(it) }
            ) {
                viewModel.register(
                    username,
                    password,
                    dateOfBirth,
                    gender,
                    height,
                    weight,
                    activityLevel,
                    weightGoal,
                    goalSteps
                )
            }
        }
        composable(NavigationItem.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
    }
}