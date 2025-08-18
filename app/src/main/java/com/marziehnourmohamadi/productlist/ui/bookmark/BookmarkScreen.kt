package com.marziehnourmohamadi.productlist.ui.bookmark

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marziehnourmohamadi.productlist.navigation.Routes
import com.marziehnourmohamadi.productlist.ui.commen.ProductListItem
import com.marziehnourmohamadi.productlist.utils.onError
import com.marziehnourmohamadi.productlist.utils.onLoading
import com.marziehnourmohamadi.productlist.utils.onSuccess

@Composable
fun BookmarkScreen(navController: NavController, viewModel: BookmarkViewModel = hiltViewModel()) {

    val itemList by viewModel.productItemList.collectAsState()

    itemList
        .onLoading { CircularProgressIndicator() }
        .onError { msg -> Snackbar { Text(msg) } }
        .onSuccess { data ->
            LazyColumn {
                items(count = data.size, key = { item -> data[item].id }) { index ->
                    ProductListItem(model = data[index]) {
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("product", data[index])
                        navController.navigate(Routes.Detail.route)
                    }
                }
            }
        }
}