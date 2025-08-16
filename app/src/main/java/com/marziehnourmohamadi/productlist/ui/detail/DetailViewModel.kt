package com.marziehnourmohamadi.productlist.ui.detail

import androidx.lifecycle.ViewModel
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    fun toggleBookmark(item: ProductItemModel) {}

}