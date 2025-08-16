package com.marziehnourmohamadi.productlist.data.remote.model

import com.marziehnourmohamadi.productlist.domain.model.RatingModel

fun RatingResponse.toDomainModel() = RatingModel(
    count= count,
    rate= rate
)