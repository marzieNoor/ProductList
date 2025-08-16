package com.marziehnourmohamadi.productlist.di

import com.marziehnourmohamadi.productlist.data.remote.api.ProductApiService
import com.marziehnourmohamadi.productlist.data.repository.ProductRepositoryImpl
import com.marziehnourmohamadi.productlist.domain.repository.ProductRepository
import com.marziehnourmohamadi.productlist.utils.Constants.BASE_URL
import com.marziehnourmohamadi.productlist.utils.ResponseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideProductRepository(api: ProductApiService, responseHelper: ResponseHelper): ProductRepository =
        ProductRepositoryImpl(api, responseHelper)

}
