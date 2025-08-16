package com.marziehnourmohamadi.productlist.data.remote.api

import com.marziehnourmohamadi.productlist.data.remote.model.ProductListResponsive
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("products")
    suspend fun getProducts():  Response<ProductListResponsive>
}