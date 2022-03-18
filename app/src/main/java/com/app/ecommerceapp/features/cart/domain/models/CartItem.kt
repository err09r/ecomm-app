package com.app.ecommerceapp.features.cart.domain.models

data class CartItem(
    val id: Int,
    val src: String,
    val price: Int,
    val title: String
)