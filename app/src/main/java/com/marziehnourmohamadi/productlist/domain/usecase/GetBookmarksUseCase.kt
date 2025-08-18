package com.marziehnourmohamadi.productlist.domain.usecase

import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): Flow<RequestState<List<ProductItemModel>>> = flow {
        emit(RequestState.Loading)
        try {
            productRepository.getBookmarks().collect { productList ->
                emit(RequestState.Success(productList))
            }
        } catch (e: Exception) {
            emit(RequestState.Error(e.localizedMessage ?: "An error has occurred"))
        }
    }
}