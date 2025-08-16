package com.marziehnourmohamadi.productlist.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class RatingResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("rate")
    val rate: Double
)