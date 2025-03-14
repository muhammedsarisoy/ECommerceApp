package com.example.ecommerceapp.ui.data.dto

data class ProductsDtoItem(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val success : Boolean,
    val price: Double,
    val rating: RatingDto,
    val title: String
)