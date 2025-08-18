package com.marziehnourmohamadi.productlist.domain.repository

import com.marziehnourmohamadi.productlist.data.remote.model.ProductListResponsive
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Result<ProductListResponsive>
    suspend fun getBookmarks():  Flow<List<ProductItemModel>>
}