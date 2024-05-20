package com.example.fitnessapp1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessapp1.ScheduledTask
import com.example.fitnessapp1.screen.CreateMealScreen
import com.example.fitnessapp1.screen.ForgotPasswordScreen
import com.example.fitnessapp1.screen.LoginScreen
import com.example.fitnessapp1.screen.MainScreen
import com.example.fitnessapp1.screen.MealScreen
import com.example.fitnessapp1.screen.SearchScreen
import com.example.fitnessapp1.screen.UpdateHeightScreen
import com.example.fitnessapp1.screen.UpdateWeightScreen
import com.example.fitnessapp1.screen.WelcomeScreen
import com.example.fitnessapp1.screen.register.ActivityLevelScreen
import com.example.fitnessapp1.screen.register.HeightAndWeightScreen
import com.example.fitnessapp1.screen.register.PersonalInformationScreen
import com.example.fitnessapp1.screen.register.RegisterScreen
import com.example.fitnessapp1.screen.register.WeightGoalScreen
import com.example.fitnessapp1.view_model.ActivityStatViewModel
import com.example.fitnessapp1.view_model.HeightChangeViewModel
import com.example.fitnessapp1.view_model.LoginViewModel
import com.example.fitnessapp1.view_model.MealStatViewModel
import com.example.fitnessapp1.view_model.MealViewModel
import com.example.fitnessapp1.view_model.ProfileViewModel
import com.example.fitnessapp1.view_model.RegisterViewModel
import com.example.fitnessapp1.view_model.WeightChangeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Welcome.route,
    registerViewModel: RegisterViewModel = getViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Welcome.route) {
            val loginViewModel = getViewModel<LoginViewModel>()
            val state by loginViewModel.loginState.collectAsState()
            WelcomeScreen(
                state = state,
                onStateChange = { loginViewModel.saveState(it) },
                onAutoLogin = { loginViewModel.autoLogin() },
                goToMain = { navController.navigate(NavigationItem.Main.route) },
                onRegisterClick = { navController.navigate(NavigationItem.WeightGoal.route) },
                onLoginClick = { navController.navigate(NavigationItem.Login.route) }
            )
        }
        composable(NavigationItem.WeightGoal.route) {
            WeightGoalScreen(
                onArrowClick = { navController.popBackStack() },
                onWeightGoalSelected = { registerViewModel.saveWeightGoal(it) },
                onNextClick = { navController.navigate(NavigationItem.ActivityLevel.route) }
            )
        }
        composable(NavigationItem.ActivityLevel.route) {
            ActivityLevelScreen(
                onArrowClick = { navController.popBackStack() },
                onActivityLevelSelected = { registerViewModel.saveActivityLevel(it) },
                onNextClick = { navController.navigate(NavigationItem.PersonalInformation.route) }
            )
        }
        composable(NavigationItem.PersonalInformation.route) {
            val dateOfBirth by registerViewModel.dateOfBirth.collectAsState()
            val goalSteps by registerViewModel.goalSteps.collectAsState()
            PersonalInformationScreen(
                onArrowClick = { navController.popBackStack() },
                onPersonalInformationSelected = { registerViewModel.saveGender(it) },
                dateOfBirth = dateOfBirth,
                onDateOfBirthChange = { registerViewModel.saveDateOfBirth(it) },
                goalSteps = goalSteps,
                onGoalStepsChange = { registerViewModel.saveGoalSteps(it) },
                onNextClick = { navController.navigate(NavigationItem.HeightAndWeight.route) }
            )
        }
        composable(NavigationItem.HeightAndWeight.route) {
            val height by registerViewModel.height.collectAsState()
            val weight by registerViewModel.weight.collectAsState()
            HeightAndWeightScreen(
                onArrowClick = { navController.popBackStack() },
                height = height,
                onHeightChange = { registerViewModel.saveHeight(it) },
                weight = weight,
                onWeightChange = { registerViewModel.saveWeight(it) },
                onNextClick = { navController.navigate(NavigationItem.Register.route) }
            )
        }
        composable(NavigationItem.Login.route) {
            val loginViewModel = getViewModel<LoginViewModel>()
            val state by loginViewModel.loginState.collectAsState()
            val email by loginViewModel.email.collectAsState()
            val password by loginViewModel.password.collectAsState()
            LoginScreen(
                state = state,
                onStateChange = { loginViewModel.saveState(it) },
                email = email,
                onEmailChange = { loginViewModel.saveEmail(it) },
                password = password,
                onPasswordChange = { loginViewModel.savePassword(it) },
                onButtonClick = { loginViewModel.login(email, password) },
                onArrowClick = { navController.navigate(NavigationItem.Welcome.route) },
                onForgotPasswordClick = { navController.navigate(NavigationItem.ForgotPassword.route) },
                onRegisterClick = { navController.navigate(NavigationItem.WeightGoal.route) },
                onLoginClick = { navController.navigate(NavigationItem.Main.route) }
            )
        }
        composable(NavigationItem.Register.route) {
            val state by registerViewModel.registerState.collectAsState()
            val email by registerViewModel.email.collectAsState()
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
                state = state,
                onStateChange = { registerViewModel.saveState(it) },
                email = email,
                onEmailChange = { registerViewModel.saveEmail(it) },
                username = username,
                onUsernameChange = { registerViewModel.saveUsername(it) },
                password = password,
                onPasswordChange = { registerViewModel.savePassword(it) },
                onButtonClick = {
                    registerViewModel.register(
                        email,
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
                },
                onArrowClick = { navController.popBackStack() },
                onRegisterClick = { navController.navigate(NavigationItem.Login.route) },
                onLoginClick = { navController.navigate(NavigationItem.Login.route) }
            )
        }
        composable(NavigationItem.Main.route) {
            val profileViewModel = getViewModel<ProfileViewModel>()
            val activityStatViewModel = getViewModel<ActivityStatViewModel>()
            val heightChangesViewModel = getViewModel<HeightChangeViewModel>()
            val weightChangesViewModel = getViewModel<WeightChangeViewModel>()
            val loginViewModel = getViewModel<LoginViewModel>()
            val profileState by profileViewModel.profileState.collectAsState()
            val activityStatState by activityStatViewModel.activityStatState.collectAsState()
            val heightChangesState by heightChangesViewModel.heightChangesState.collectAsState()
            val weightChangesState by weightChangesViewModel.weightChangesState.collectAsState()
            val logoutState by loginViewModel.loginState.collectAsState()
            val profile by profileViewModel.profile.collectAsState()
            val activityStat by activityStatViewModel.activityStat.collectAsState()
            val steps by activityStatViewModel.steps.collectAsState()
            val water by activityStatViewModel.water.collectAsState()
            val heightChanges by heightChangesViewModel.changes.collectAsState()
            val weightChanges by weightChangesViewModel.changes.collectAsState()
            MainScreen(
                profileState = profileState,
                onProfileStateChange = { profileViewModel.saveState(it) },
                activityStatState = activityStatState,
                onActivityStatStateChange = { activityStatViewModel.saveState(it) },
                heightChangesState = heightChangesState,
                onHeightChangesStateChange = { heightChangesViewModel.saveState(it) },
                weightChangesState = weightChangesState,
                onWeightChangesStateChange = { weightChangesViewModel.saveState(it) },
                logoutState = logoutState,
                onLogoutStateChange = { loginViewModel.saveState(it) },
                profile = profile,
                onGetProfile = { profileViewModel.getProfile() },
                activityStat = activityStat,
                onGetActivityStat = { activityStatViewModel.getActivityStat() },
                steps = steps,
                heightChanges = heightChanges,
                onGetHeightChanges = { heightChangesViewModel.getChanges() },
                weightChanges = weightChanges,
                onLogoutClick = { loginViewModel.logout() },
                onGetWeightChanges = { weightChangesViewModel.getChanges() },
                onStepsChange = { activityStatViewModel.saveSteps(it) },
                onWaterChange = { activityStatViewModel.saveWater(it) },
                updateActivityStat = {
                    activityStatViewModel
                        .updateActivityStat(steps, water)
                },
                onClickUpdateWeight = { navController.navigate(NavigationItem.UpdateWeight.route) },
                onClickUpdateHeight = { navController.navigate(NavigationItem.UpdateHeight.route) },
                onAddMealClick = { navController.navigate(NavigationItem.Search.route) },
                onCreateMealClick = { navController.navigate(NavigationItem.CreateMeal.route) },
                goToWelcomeScreen = { navController.navigate(NavigationItem.Welcome.route) }
            )

            val scheduler = ScheduledTask
            LaunchedEffect(key1 = true) {
                scheduler.performTask(
                    taskToRun = {
                        activityStatViewModel.updateActivityStat(steps, 0f)
                    },
                    hour = 23,
                    minute = 59,
                    second = 59
                )
            }
        }
        composable(NavigationItem.ForgotPassword.route) {
            ForgotPasswordScreen()
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
                onMealNameChange = { mealViewModel.saveMealName(it) },
                active = active,
                onActiveChange = { mealStatViewModel.saveActive(it) },
                onSearchButtonClick = { mealViewModel.searchMealByName(mealName) },
                meals = meals,
                onMealIdChange = { mealStatViewModel.saveMealId(it) }
            )
        }
        composable(NavigationItem.CreateMeal.route) {
            val mealViewModel = getViewModel<MealViewModel>()
            val state by mealViewModel.mealsState.collectAsState()
            val mealName by mealViewModel.mealName.collectAsState()
            val calories by mealViewModel.calories.collectAsState()
            val protein by mealViewModel.protein.collectAsState()
            val carbs by mealViewModel.carbs.collectAsState()
            val fat by mealViewModel.fat.collectAsState()
            CreateMealScreen(
                state = state,
                onStateChange = { mealViewModel.saveState(it) },
                mealName = mealName,
                onMealNameChange = { mealViewModel.saveMealName(it) },
                calories = calories,
                onCaloriesChange = { mealViewModel.saveCalories(it) },
                protein = protein,
                onProteinChange = { mealViewModel.saveProtein(it) },
                carbs = carbs,
                onCarbsChange = { mealViewModel.saveCarbs(it) },
                fat = fat,
                onFatChange = { mealViewModel.saveFat(it) },
                onButtonClick = {
                    mealViewModel.createMeal(
                        mealName,
                        calories,
                        protein,
                        carbs,
                        fat
                    )
                },
                goToMain = { navController.navigate(NavigationItem.Main.route) },
                onArrowClick = { navController.popBackStack() }
            )
        }
        composable("${NavigationItem.Meal.route}/{id}") { it ->
            val id = it.arguments?.getString("id")
            val mealViewModel = getViewModel<MealViewModel>()
            val mealStatViewModel = getViewModel<MealStatViewModel>()
            val state by mealStatViewModel.mealStatState.collectAsState()
            val meal by mealViewModel.meal.collectAsState()
            val portion by mealStatViewModel.portion.collectAsState()
            val type by mealStatViewModel.type.collectAsState()
            MealScreen(
                onGetMeal = { mealViewModel.getMealById(id) },
                state = state,
                onStateChange = { mealStatViewModel.saveState(it) },
                meal = meal,
                portion = portion,
                onPortionChange = { mealStatViewModel.savePortion(it) },
                onMealTypeChange = { mealStatViewModel.saveMealType(it) },
                onAddMealToPlan = {
                    mealStatViewModel.createMealStat(portion, type, id.toString())
                },
                onAddMealToPlanClick = { navController.navigate(NavigationItem.Main.route) },
                onArrowClick = { navController.popBackStack() }
            )
        }
        composable(NavigationItem.UpdateHeight.route) {
            val heightChangeViewModel = getViewModel<HeightChangeViewModel>()
            val state by heightChangeViewModel.heightChangesState.collectAsState()
            val height by heightChangeViewModel.height.collectAsState()
            UpdateHeightScreen(
                state = state,
                onStateChange = { heightChangeViewModel.saveState(it) },
                height = height,
                onHeightChange = { heightChangeViewModel.saveHeight(it) },
                onButtonClick = { heightChangeViewModel.updateHeight(height) },
                onArrowClick = { navController.popBackStack() }
            )
        }
        composable(NavigationItem.UpdateWeight.route) {
            val weightChangesViewModel = getViewModel<WeightChangeViewModel>()
            val state by weightChangesViewModel.weightChangesState.collectAsState()
            val weight by weightChangesViewModel.weight.collectAsState()
            UpdateWeightScreen(
                state = state,
                onStateChange = { weightChangesViewModel.saveState(it) },
                weight = weight,
                onWeightChange = { weightChangesViewModel.saveWeight(it) },
                onButtonClick = { weightChangesViewModel.updateWeight(weight) },
                onArrowClick = { navController.popBackStack() }
            )
        }
    }
}
