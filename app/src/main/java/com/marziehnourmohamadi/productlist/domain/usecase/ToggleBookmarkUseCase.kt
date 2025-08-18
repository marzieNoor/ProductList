package com.marziehnourmohamadi.productlist.domain.usecase

import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(bookmarked: ProductItemModel): Flow<RequestState<Boolean>> = flow {
        emit(RequestState.Loading)
        try {
            val fetchResult = productRepository.toggleBookmark(bookmarked)
            emit(RequestState.Success(fetchResult))
        } catch (e: Exception) {
            emit(RequestState.Error(e.localizedMessage ?: "An error has occurred"))
        }
    }
}