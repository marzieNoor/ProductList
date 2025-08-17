package com.marziehnourmohamadi.productlist.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marziehnourmohamadi.productlist.navigation.Routes
import com.marziehnourmohamadi.productlist.ui.commen.ProductListItem
import com.marziehnourmohamadi.productlist.utils.onError
import com.marziehnourmohamadi.productlist.utils.onLoading
import com.marziehnourmohamadi.productlist.utils.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val itemList by viewModel.filteredList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .padding(end = 18.dp, start = 18.dp)
            .fillMaxSize()
    ) {

        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it) },
            onSearch = { viewModel.onSearchQueryChange(it) },
            active = false,
            onActiveChange = { },
            placeholder = { Text("Search products...") },
            modifier = Modifier.fillMaxWidth()
        ) {}

        Spacer(modifier = Modifier.height(20.dp))

        itemList
            .onLoading { CircularProgressIndicator() }
            .onError { msg -> Snackbar { Text(msg) } }
            .onSuccess { data ->
                Log.e("itemList", "HomeScreen: ${data.size}")

                LazyColumn {
                    items(count = data.size) { index ->

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
}