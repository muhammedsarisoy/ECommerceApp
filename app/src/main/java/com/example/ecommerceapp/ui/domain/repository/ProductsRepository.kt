package com.example.ecommerceapp.ui.domain.repository

import com.example.ecommerceapp.ui.data.dto.ProductsDto
import com.example.ecommerceapp.ui.data.dto.ProductsDtoItem
import com.example.ecommerceapp.ui.domain.model.Products
import retrofit2.Response


interface ProductsRepository {

    suspend fun getProducts(): Response<List<ProductsDtoItem>>

    suspend fun getProductById(id: Int): Response<ProductsDtoItem>
}
