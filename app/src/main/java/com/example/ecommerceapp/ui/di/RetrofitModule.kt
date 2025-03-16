package com.example.ecommerceapp.ui.di

import com.example.ecommerceapp.ui.data.remote.ProductsApi
import com.example.ecommerceapp.ui.data.repository.ProductsRepositoryImpl
import com.example.ecommerceapp.ui.domain.repository.ProductsRepository
import com.example.ecommerceapp.ui.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)

    @Provides
    @Singleton
    fun provideProductsRepository(api: ProductsApi): ProductsRepository {
        return ProductsRepositoryImpl(api)
    }
}