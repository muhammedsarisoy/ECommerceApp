package com.example.ecommerceapp.ui.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecommerceapp.ui.utils.Screen


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.background
    val selectedColor = Color(0xFFFFA500)
    val unselectedColor = Color.Gray

    NavigationBar(
        modifier = modifier,
        containerColor = backgroundColor
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentDestination = navBackStackEntry?.destination
        val items = listOf(
            Screen.Home,
            Screen.Favorite,
            Screen.Order,
            Screen.Profile
        )

        items.forEach { item ->
            val isSelected = currentDestination?.route == item.route


            val iconSize by animateFloatAsState(
                targetValue = if (isSelected) 24f else 20f,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
                label = "iconSize"
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigate(item.route){
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (isSelected) selectedColor else unselectedColor,
                        modifier = Modifier.size(iconSize.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) selectedColor else unselectedColor
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = backgroundColor,
                    selectedIconColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    selectedTextColor = selectedColor,
                    unselectedTextColor = unselectedColor
                )
            )
        }
    }
}

