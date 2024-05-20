package com.example.fitnessapp1.navigation

enum class Screen {
    WELCOME,
    WEIGHT_GOAL,
    ACTIVITY_LEVEL,
    PERSONAL_INFORMATION,
    HEIGHT_AND_WEIGHT,
    MAIN,
    LOGIN,
    REGISTER,
    FORGOT_PASSWORD,
    SEARCH,
    MEAL,
    CREATE_MEAL,
    UPDATE_HEIGHT,
    UPDATE_WEIGHT
}
sealed class NavigationItem(val route: String) {
    object Welcome : NavigationItem(Screen.WELCOME.name)
    object WeightGoal : NavigationItem(Screen.WEIGHT_GOAL.name)
    object ActivityLevel : NavigationItem(Screen.ACTIVITY_LEVEL.name)
    object PersonalInformation : NavigationItem(Screen.PERSONAL_INFORMATION.name)
    object HeightAndWeight : NavigationItem(Screen.HEIGHT_AND_WEIGHT.name)
    object Main : NavigationItem(Screen.MAIN.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object ForgotPassword : NavigationItem(Screen.FORGOT_PASSWORD.name)
    object Search : NavigationItem(Screen.SEARCH.name)
    object Meal : NavigationItem(Screen.MEAL.name)
    object CreateMeal : NavigationItem(Screen.CREATE_MEAL.name)
    object UpdateHeight : NavigationItem(Screen.UPDATE_HEIGHT.name)
    object UpdateWeight : NavigationItem(Screen.UPDATE_WEIGHT.name)
}