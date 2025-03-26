package com.example.ecommerceapp.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String , val title: String , val icon: ImageVector){
    object Home: Screen("HomeScreen" , "Home" , Icons.Default.Home)
    object Favorite: Screen("FavoriteScreen" , "Favorite" , Icons.Default.Favorite)
    object Order: Screen("OrderScreen" , "Order" , Icons.Default.ShoppingCart)
    object Profile: Screen("ProfileScreen" , "Profile" , Icons.Default.Person)
    object Login: Screen("LoginScreen" , "Login" , Icons.Default.Person)
}