package com.tech.quiz_app_mvvm.presentation.nav_graph

import androidx.navigation.NavController

fun goToHome(navController: NavController) {
    navController.navigate(Routes.HomeScreen.route) {
        popUpTo(Routes.HomeScreen.route) {
            inclusive = true
        }
    }
}