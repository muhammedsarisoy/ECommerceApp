package com.example.ecommerceapp.ui.data.repository

import com.example.ecommerceapp.ui.data.dto.ProductsDtoItem
import com.example.ecommerceapp.ui.data.remote.ProductsApi
import com.example.ecommerceapp.ui.domain.model.Products
import com.example.ecommerceapp.ui.domain.repository.ProductsRepository
import retrofit2.Response
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val api: ProductsApi
) : ProductsRepository {
    override suspend fun getProducts(): Response<List<ProductsDtoItem>> {
        return api.getProducts()
    }

    override suspend fun getProductById(id: Int): Response<ProductsDtoItem> {
        return api.getProductById(id)
    }

}
