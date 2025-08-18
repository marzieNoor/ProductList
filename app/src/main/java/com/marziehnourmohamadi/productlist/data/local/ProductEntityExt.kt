package com.marziehnourmohamadi.productlist.data.local

import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel
import com.marziehnourmohamadi.productlist.domain.model.RatingModel


fun ProductEntity.toDomainModel() = ProductItemModel(
    category = category,
    description = description,
    id = id,
    image = image,
    price = price,
    rating = RatingModel(count = ratingCount, rate = ratingRate),
    title = title
)

fun ProductItemModel.toEntityModel() = ProductEntity(
    category = category,
    description = description,
    id = id,
    image = image,
    price = price,
    ratingCount = rating.count,
    ratingRate = rating.rate,
    title = title
)