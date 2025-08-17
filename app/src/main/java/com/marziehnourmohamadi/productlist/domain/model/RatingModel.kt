package com.marziehnourmohamadi.productlist.domain.model

import java.io.Serializable


data class RatingModel(
    val count: Int,
    val rate: Double
): Serializable