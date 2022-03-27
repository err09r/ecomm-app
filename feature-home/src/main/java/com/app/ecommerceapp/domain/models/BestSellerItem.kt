package com.app.ecommerceapp.domain.models

data class BestSellerItem(
    val id: Int,
    val fullPrice: Int,
    val finalPrice: Int,
    val title: String,
    val src: String,
    val isFavorites: Boolean
)