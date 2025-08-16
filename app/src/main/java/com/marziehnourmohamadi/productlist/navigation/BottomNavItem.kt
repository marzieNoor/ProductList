package com.marziehnourmohamadi.productlist.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Default.Home, Routes.Home.route)
    object Bookmarks : BottomNavItem("Bookmarks", Icons.Default.Favorite, Routes.Bookmark.route)
}