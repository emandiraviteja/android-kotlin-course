package com.example.a71_jetpackcompose


/**Created by Raviteja Emandi on 12,July,2025 **/

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a71_jetpackcompose.screens.CounterScreen
import com.example.a71_jetpackcompose.screens.ListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "counter") {
        composable("counter") {
            CounterScreen(navController)
        }
        composable("list") {
            ListScreen(navController)
        }
    }
}
