package com.marziehnourmohamadi.productlist.di

import android.content.Context
import androidx.room.Room
import com.marziehnourmohamadi.productlist.data.local.ProductDao
import com.marziehnourmohamadi.productlist.data.remote.api.ProductApiService
import com.marziehnourmohamadi.productlist.data.repository.ProductRepositoryImpl
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.AppDatabase
import com.marziehnourmohamadi.productlist.utils.Constants.BASE_URL
import com.marziehnourmohamadi.productlist.utils.ResponseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideBlogApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApiService,
        productDao: ProductDao,
        responseHelper: ResponseHelper
    ): ProductRepository =
        ProductRepositoryImpl(api = api, bookmarkDao = productDao, responseHelper = responseHelper)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "bookmarks_db").build()

    @Provides
    fun provideVideoDao(db: AppDatabase): ProductDao = db.productDao()

}
