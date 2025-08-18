package com.marziehnourmohamadi.productlist.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Bookmark : Routes("bookmark")
    object Detail : Routes("detail/{productJson}")
}
