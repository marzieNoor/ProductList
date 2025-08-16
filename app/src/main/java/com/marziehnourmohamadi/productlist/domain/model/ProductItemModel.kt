package com.marziehnourmohamadi.productlist.domain.model


data class ProductItemModel(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingModel,
    val title: String
)