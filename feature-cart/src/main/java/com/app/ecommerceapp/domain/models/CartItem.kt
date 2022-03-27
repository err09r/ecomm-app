package com.app.ecommerceapp.domain.models

data class CartItem(
    val id: Int,
    val src: String,
    val price: Int,
    val title: String,
    val amount: Int
)