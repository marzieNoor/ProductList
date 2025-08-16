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
    val productItemList: StateFlow<RequestState<List<ProductItemModel>>> = _productItemList

    init {
        getProductList()
    }

    fun getProductList() {
        viewModelScope.launch {
            getProductListUseCase.invoke().collect { state ->
                _productItemList.value = state
            }
        }

    }
}