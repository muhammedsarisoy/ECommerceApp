package com.example.ecommerceapp.ui.data.mapper

import com.example.ecommerceapp.ui.data.dto.ProductsDtoItem
import com.example.ecommerceapp.ui.domain.model.Products


fun ProductsDtoItem.toProducts(): Products {
    return Products(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating,
        title = title
    )
}
