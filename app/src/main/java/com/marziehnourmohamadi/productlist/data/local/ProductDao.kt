package com.marziehnourmohamadi.productlist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Upsert()
    suspend fun insertBookmark(entity: ProductEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    suspend fun isBookmarked(id: Int): Boolean

    @Query("SELECT * FROM bookmarks ORDER BY price DESC")
    fun getAllBookmarks(): Flow<List<ProductEntity>>

    @Delete
    suspend fun deleteBookmark(entity: ProductEntity): Int
}