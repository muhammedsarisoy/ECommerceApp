package com.example.ecommerceapp.ui.data.mapper

import com.example.ecommerceapp.ui.data.dto.RatingDto
import com.example.ecommerceapp.ui.domain.model.Rating


fun RatingDto.toRating(): Rating {
    return Rating(
        count = this.count,
        rate = this.rate
    )
}