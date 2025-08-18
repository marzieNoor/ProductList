package com.marziehnourmohamadi.productlist.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marziehnourmohamadi.productlist.data.local.ProductDao
import com.marziehnourmohamadi.productlist.data.local.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
