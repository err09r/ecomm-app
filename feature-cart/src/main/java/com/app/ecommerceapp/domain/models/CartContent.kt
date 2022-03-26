package com.app.ecommerceapp.domain.models

data class CartContent(
    val cart: List<CartItem>,
    val delivery: String,
    val total: Int
)