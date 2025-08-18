package com.marziehnourmohamadi.productlist.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.usecase.GetBookmarksUseCase
import com.marziehnourmohamadi.productlist.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkUesCase: GetBookmarksUseCase
) : ViewModel() {

    private val _productItemList =
        MutableStateFlow<RequestState<List<ProductItemModel>>>(RequestState.Idle)
    val productItemList: StateFlow<RequestState<List<ProductItemModel>>> = _productItemList

    init {
        getBookmarks()
    }

    private fun getBookmarks() {
        viewModelScope.launch {
            getBookmarkUesCase().collect { state ->
                _productItemList.value = state
            }
        }
    }
}
