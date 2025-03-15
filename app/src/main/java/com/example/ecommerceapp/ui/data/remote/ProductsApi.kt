package com.example.ecommerceapp.ui.data.remote

import com.example.ecommerceapp.ui.data.dto.ProductsDtoItem
import com.example.ecommerceapp.ui.domain.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path



interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): Response<List<ProductsDtoItem>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductsDtoItem>
}
