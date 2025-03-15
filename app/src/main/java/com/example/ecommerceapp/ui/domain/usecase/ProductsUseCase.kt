package com.example.ecommerceapp.ui.domain.usecase

import com.example.ecommerceapp.ui.data.mapper.toProducts
import com.example.ecommerceapp.ui.domain.model.Products
import com.example.ecommerceapp.ui.domain.repository.ProductsRepository
import com.example.ecommerceapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    fun getProducts(): Flow<Resource<List<Products>>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getProducts()
            if (response.isSuccessful) {
                response.body()?.let { dtoList ->
                    emit(Resource.Success(dtoList.map { it.toProducts() }))
                } ?: emit(Resource.Error("Response body is null"))
            } else {
                emit(Resource.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.localizedMessage}"))
        }
    }

    fun getProductById(id: Int): Flow<Resource<Products>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.getProductById(id)
            if (response.isSuccessful) {
                response.body()?.let { dto ->
                    emit(Resource.Success(dto.toProducts()))
                } ?: emit(Resource.Error("Response body is null"))
            } else {
                emit(Resource.Error("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.localizedMessage}"))
        }
    }
}
