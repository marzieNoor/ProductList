package com.marziehnourmohamadi.productlist.data.repository


import com.marziehnourmohamadi.productlist.data.remote.api.ProductApiService
import com.marziehnourmohamadi.productlist.data.remote.model.ProductListResponsive
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.ResponseHelper

import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService,
    private val responseHelper: ResponseHelper
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductListResponsive> {
        return responseHelper.parseApiResult { api.getProducts() }
    }
}