package com.example.ecommerceapp.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.ui.utils.Menu
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    isVisible: Boolean
) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState()

    val items = listOf(
        Menu("Home", Icons.Default.Home, Icons.Default.Home, 4),
        Menu("Cart", Icons.Default.ShoppingCart, Icons.Default.ShoppingCart, 10),
        Menu("Settings", Icons.Default.Settings, Icons.Default.Settings),
        Menu("Profile", Icons.Default.Person, Icons.Default.Person),
        Menu("Logout", Icons.Default.ExitToApp, Icons.Default.Person),
    )

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = bottomSheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "My App",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }

                // Menu Items
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                if (!bottomSheetState.isVisible) {
                                    onDismiss()
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = it.toString())
                            }
                        },
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}