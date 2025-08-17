package com.marziehnourmohamadi.productlist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.usecase.GetProductListUseCase
import com.marziehnourmohamadi.productlist.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val _productItemList =
        MutableStateFlow<RequestState<List<ProductItemModel>>>(RequestState.Idle)

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredList =
        MutableStateFlow<RequestState<List<ProductItemModel>>>(RequestState.Idle)
    val filteredList: StateFlow<RequestState<List<ProductItemModel>>> = _filteredList

    init {
        getProductList()
    }

    fun getProductList() {
        viewModelScope.launch {
            getProductListUseCase.invoke().collect { state ->
                _productItemList.value = state
                applyFilter()
            }
        }

    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        applyFilter()
    }

    private fun applyFilter() {
        val currentState = _productItemList.value
        if (currentState is RequestState.Success) {
            val filtered = if (_searchQuery.value.isBlank()) {
                currentState.data
            } else {
                currentState.data.filter {
                    it.title.contains(_searchQuery.value, ignoreCase = true)
                }
            }
            _filteredList.value = RequestState.Success(filtered)
        } else {
            _filteredList.value = currentState
        }
    }
}