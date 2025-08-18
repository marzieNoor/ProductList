package com.marziehnourmohamadi.productlist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val ratingCount: Int,
    val ratingRate: Double,
    val title: String,
)