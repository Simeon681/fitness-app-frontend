package com.example.fitnessapp1.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitnessapp1.screen.ForgotPasswordScreen
import com.example.fitnessapp1.screen.LoginScreen
import com.example.fitnessapp1.screen.MainScreen
import com.example.fitnessapp1.screen.RegisterScreen

@RequiresApi(Build.VERSION_CODES.O)
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
            LoginScreen(navController)
        }
        composable(NavigationItem.Register.route) {
            RegisterScreen(navController)
        }
        composable(NavigationItem.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
    }
}