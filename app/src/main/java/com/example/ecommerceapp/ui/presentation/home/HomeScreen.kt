package com.example.ecommerceapp.ui.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.ecommerceapp.ui.domain.model.Products


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.productsState.value

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error?.isNotBlank() == true -> {
                Text(
                    text = state.error.toString(),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                ProductsItem(products = state.products)
            }
        }
    }
}

@Composable
fun ProductsItem(
    products: List<Products>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Composable
fun ProductCard(product: Products) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp , 200.dp)
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = null,
            modifier = Modifier.size(100.dp , 100.dp)
        )
        Text(text = product.title, modifier = Modifier.padding(8.dp))
        Text(text = "$${product.price}", modifier = Modifier.padding(8.dp))
    }
}