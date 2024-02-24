package com.example.fitnessapp1.navigation

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
import com.example.fitnessapp1.screen.MealScreen
import com.example.fitnessapp1.screen.RegisterScreen
import com.example.fitnessapp1.screen.SearchScreen
import com.example.fitnessapp1.view_model.ActivityStatViewModel
import com.example.fitnessapp1.view_model.LoginViewModel
import com.example.fitnessapp1.view_model.MealStatViewModel
import com.example.fitnessapp1.view_model.MealViewModel
import com.example.fitnessapp1.view_model.ProfileViewModel
import com.example.fitnessapp1.view_model.RegisterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            val profileViewModel = getViewModel<ProfileViewModel>()
            val activityStatViewModel = getViewModel<ActivityStatViewModel>()
            val mealStatViewModel = getViewModel<MealStatViewModel>()
            val profile by profileViewModel.profile.collectAsState()
            val activityStat by activityStatViewModel.activityStat.collectAsState()
            val steps by activityStatViewModel.steps.collectAsState()
            val water by activityStatViewModel.water.collectAsState()
            val mealId by mealStatViewModel.mealId.collectAsState()
            val type by mealStatViewModel.type.collectAsState()
            MainScreen(
                navController,
                profile = profile,
                onGetProfile = { profileViewModel.getProfile() },
                activityStat = activityStat,
                onGetActivityStat = { activityStatViewModel.getActivityStat() },
                steps = steps,
                onStepsChange = { activityStatViewModel.setSteps(it) },
                onWaterChange = { activityStatViewModel.setWater(it) },
                updateActivityStat = {
                    activityStatViewModel
                        .updateActivityStat(steps, water)
                },
                mealId = mealId,
                onMealIdChange = { mealStatViewModel.setMealId(it) },
                type = type,
                onMealTypeChange = { mealStatViewModel.setMealType(it) },
                onMealStatButtonClick = {
                    //mealStatViewModel.createMealStat(mealId, portion, mealType)
                }
            )
        }
        composable(NavigationItem.Login.route) {
            val loginViewModel = getViewModel<LoginViewModel>()
            val username by loginViewModel.username.collectAsState()
            val password by loginViewModel.password.collectAsState()
            LoginScreen(
                navController,
                username = username,
                onUsernameChange = { loginViewModel.saveUsername(it) },
                password = password,
                onPasswordChange = { loginViewModel.savePassword(it) },
                onButtonClick = { loginViewModel.login(username, password) }
            )
        }
        composable(NavigationItem.Register.route) {
            val registerViewModel = getViewModel<RegisterViewModel>()
            val username by registerViewModel.username.collectAsState()
            val password by registerViewModel.password.collectAsState()
            val dateOfBirth by registerViewModel.dateOfBirth.collectAsState()
            val gender by registerViewModel.gender.collectAsState()
            val height by registerViewModel.height.collectAsState()
            val weight by registerViewModel.weight.collectAsState()
            val activityLevel by registerViewModel.activityLevel.collectAsState()
            val weightGoal by registerViewModel.weightGoal.collectAsState()
            val goalSteps by registerViewModel.goalSteps.collectAsState()
            RegisterScreen(
                navController,
                username = username,
                onUsernameChange = { registerViewModel.setUsername(it) },
                password = password,
                onPasswordChange = { registerViewModel.setPassword(it) },
                dateOfBirth = dateOfBirth,
                onDateOfBirthChange = { registerViewModel.setDateOfBirth(it) },
                gender = gender,
                onGenderChange = { registerViewModel.setGender(it) },
                height = height,
                onHeightChange = { registerViewModel.setHeight(it) },
                weight = weight,
                onWeightChange = { registerViewModel.setWeight(it) },
                activityLevel = activityLevel,
                onActivityLevelChange = { registerViewModel.setActivityLevel(it) },
                weightGoal = weightGoal,
                onWeightGoalChange = { registerViewModel.setWeightGoal(it) },
                goalSteps = goalSteps,
                onGoalStepsChange = { registerViewModel.setGoalSteps(it) },
                onButtonClick = {
                    registerViewModel.register(
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
            )
        }
        composable(NavigationItem.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(NavigationItem.Search.route) {
            val mealViewModel = getViewModel<MealViewModel>()
            val mealStatViewModel = getViewModel<MealStatViewModel>()
            val active by mealStatViewModel.active.collectAsState()
            val mealName by mealViewModel.mealName.collectAsState()
            val meals by mealViewModel.meals.collectAsState()
            SearchScreen(
                navController,
                mealName = mealName,
                onMealNameChange = { mealViewModel.setMealName(it) },
                active = active,
                onActiveChange = { mealStatViewModel.setActive(it) },
                onSearchButtonClick = { mealViewModel.searchMealByName(mealName) },
                meals = meals,
                onMealIdChange = { mealStatViewModel.setMealId(it) }
            )
        }
        composable("${NavigationItem.Meal.route}/{id}") { it ->
            val id = it.arguments?.getString("id")?.toLong()
            val mealViewModel = getViewModel<MealViewModel>()
            val mealStatViewModel = getViewModel<MealStatViewModel>()
            val meal by mealViewModel.meal.collectAsState()
            val portion by mealStatViewModel.portion.collectAsState()
            val type by mealStatViewModel.type.collectAsState()
            MealScreen(
                navController,
                onGetMeal = { mealViewModel.getMealById(id) },
                meal = meal,
                portion = portion,
                onPortionChange = { mealStatViewModel.setPortion(it) },
                type = type,
                onMealTypeChange = { mealStatViewModel.setMealType(it) },
                onAddMealToPlan = {
                    mealStatViewModel.createMealStat(portion, type, id)
                }
            )
        }
    }
}
