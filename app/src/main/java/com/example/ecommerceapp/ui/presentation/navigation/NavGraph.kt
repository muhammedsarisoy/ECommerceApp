package com.example.ecommerceapp.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerceapp.ui.presentation.favorite.FavoriteScreen
import com.example.ecommerceapp.ui.presentation.home.HomeScreen
import com.example.ecommerceapp.ui.presentation.order.OrderScreen
import com.example.ecommerceapp.ui.presentation.profile.ProfileScreen
import com.example.ecommerceapp.ui.utils.Screen


@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Favorite.route) {
            FavoriteScreen(navController = navController)
        }

        composable(Screen.Order.route) {
            OrderScreen(navController = navController)
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}