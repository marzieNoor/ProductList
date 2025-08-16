package com.marziehnourmohamadi.productlist.domain.repository

import com.marziehnourmohamadi.productlist.data.remote.model.ProductListResponsive

interface ProductRepository {
    suspend fun getProducts(): Result<ProductListResponsive>

}