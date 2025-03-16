package com.example.ecommerceapp.ui.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.ui.domain.usecase.ProductsUseCase
import com.example.ecommerceapp.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
): ViewModel() {

    private val _productsState = mutableStateOf(HomeState())
    val productsState: State<HomeState> = _productsState


    init {
        getProducts()
    }

    private fun getProducts() {
        productsUseCase.getProducts().onEach {
            when (it) {
                is Resource.Loading -> {
                    _productsState.value = HomeState(isLoading = true)
                }

                is Resource.Success -> {
                    _productsState.value = HomeState(products = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _productsState.value =
                        HomeState(error = it.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}