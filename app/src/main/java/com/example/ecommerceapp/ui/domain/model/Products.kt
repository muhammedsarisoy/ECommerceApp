package com.example.ecommerceapp.ui.domain.model

import com.example.ecommerceapp.ui.data.dto.RatingDto

data class Products(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingDto,
    val title: String
)
