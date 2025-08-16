package com.marziehnourmohamadi.productlist.domain.usecase

import com.marziehnourmohamadi.productlist.data.remote.model.toDomainModel
import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<RequestState<List<ProductItemModel>>> = flow {
        emit(RequestState.Loading)

        val fetchResult = productRepository.getProducts()
        fetchResult.fold(
            onSuccess = { result ->
                    emit(RequestState.Success(result.map { it.toDomainModel() }.sortedBy { it.price }))
            },
            onFailure = { throwable ->
                emit(RequestState.Error(throwable.message ?: "An error has occurred"))
            }
        )
    }
}