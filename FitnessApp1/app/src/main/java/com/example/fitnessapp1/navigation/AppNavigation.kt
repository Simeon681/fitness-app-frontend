package com.example.fitnessapp1.navigation

enum class Screen {
    MAIN,
    LOGIN,
    REGISTER,
    FORGOT_PASSWORD,
    SEARCH,
    MEAL
}
sealed class NavigationItem(val route: String) {
    object Main : NavigationItem(Screen.MAIN.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object ForgotPassword : NavigationItem(Screen.FORGOT_PASSWORD.name)
    object Search : NavigationItem(Screen.SEARCH.name)
    object Meal : NavigationItem(Screen.MEAL.name)
}