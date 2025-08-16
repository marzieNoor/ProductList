package com.marziehnourmohamadi.productlist.data.remote.model

import com.marziehnourmohamadi.productlist.domain.model.ProductItemModel


fun ProductListResponsiveItem.toDomainModel() = ProductItemModel(
    category = category,
    description = description,
    id = id,
    image = image,
    price = price,
    rating = rating.toDomainModel(),
    title = title
)