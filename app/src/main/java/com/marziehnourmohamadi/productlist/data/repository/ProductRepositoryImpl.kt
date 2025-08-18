package com.marziehnourmohamadi.productlist.data.repository


import com.marziehnourmohamadi.productlist.data.local.ProductDao
import com.marziehnourmohamadi.productlist.data.local.toDomainModel
import com.marziehnourmohamadi.productlist.data.local.toEntityModel
import com.marziehnourmohamadi.productlist.data.remote.api.ProductApiService
import com.marziehnourmohamadi.productlist.data.remote.model.ProductListResponsive
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.ResponseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService,
    private val bookmarkDao: ProductDao,
    private val responseHelper: ResponseHelper
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductListResponsive> {
        return responseHelper.parseApiResult { api.getProducts() }
    }

    override suspend fun getBookmarks():  Flow<List<ProductItemModel>> {
        return bookmarkDao.getAllBookmarks().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun toggleBookmark(bookmarked: ProductItemModel): Boolean {
        return if (!bookmarkDao.isBookmarked(bookmarked.id)) {
            bookmarkDao.insertBookmark(bookmarked.toEntityModel())
            true
        } else {
            bookmarkDao.deleteBookmark(bookmarked.toEntityModel())
            false
        }
    }

    override suspend fun isBookmarked(bookmarked: ProductItemModel) =
        bookmarkDao.isBookmarked(bookmarked.id)
}