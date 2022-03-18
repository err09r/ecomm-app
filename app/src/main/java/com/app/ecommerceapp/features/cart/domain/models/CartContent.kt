package com.app.ecommerceapp.features.cart.domain.models

data class CartContent(
    val id: String,
    val cart: List<CartItem>,
    val delivery: String,
    val total: Int
)