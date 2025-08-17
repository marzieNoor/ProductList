package com.marziehnourmohamadi.productlist.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.navigation.BottomNavItem
import com.marziehnourmohamadi.productlist.navigation.Routes
import com.marziehnourmohamadi.productlist.ui.bookmark.BookmarkScreen
import com.marziehnourmohamadi.productlist.ui.detail.DetailScreen
import com.marziehnourmohamadi.productlist.ui.home.HomeScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Home, BottomNavItem.Bookmarks)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val barVisibleRoutes = listOf("home", "bookmark")
    val showBars = currentRoute in barVisibleRoutes
    val showBackButton = currentRoute?.startsWith("detail") == true

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(
                title = "Products",
                showBackButton = showBackButton,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        snackbarHost = { snackbarHostState },
        bottomBar = {
            if (showBars)
                NavigationBar(
                    modifier = Modifier
                        .shadow(4.dp)
                        .navigationBarsPadding(),
                ) {
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
        }
    ) { innerPadding ->
        NavGraph(navController = navController, innerPadding = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.shadow(2.dp),
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray.copy(alpha = 0.2F)
        ),
        navigationIcon = {
            if (showBackButton && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
    )
}

@Composable
fun NavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }
        composable(Routes.Bookmark.route) {
            BookmarkScreen(navController)
        }
        composable(Routes.Detail.route) { backStackEntry ->
            val product = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<ProductItemModel>("product")

            DetailScreen(product)
        }
    }
}