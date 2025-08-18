package com.marziehnourmohamadi.productlist.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.usecase.IsBookmarkedUseCase
import com.marziehnourmohamadi.productlist.domain.usecase.ToggleBookmarkUseCase
import com.marziehnourmohamadi.productlist.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
) : ViewModel() {

    private val _isBookmarkProductItem =
        MutableStateFlow<RequestState<Boolean>>(RequestState.Idle)
    val isBookmarkProductItem:
            MutableStateFlow<RequestState<Boolean>> = _isBookmarkProductItem

    fun loadBookmarkState(item: ProductItemModel) {
        viewModelScope.launch {
            isBookmarkedUseCase(item = item).collect { state->
                _isBookmarkProductItem.value = state
            }
        }

    }

    fun toggleBookmark(item: ProductItemModel) {
        viewModelScope.launch {
            toggleBookmarkUseCase(bookmarked = item).collect { state->
                _isBookmarkProductItem.value = state
            }
        }

    }
}