package com.example.ecommerceapp.ui.presentation.home

import com.example.ecommerceapp.ui.domain.model.Products

data class HomeState(
    val isLoading: Boolean = false,
    val products: List<Products> = emptyList(),
    val error: String? = null,
)