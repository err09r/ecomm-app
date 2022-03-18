package com.app.ecommerceapp.features.cart.data.api.dto

import com.app.ecommerceapp.features.cart.domain.models.CartContent
import com.google.gson.annotations.SerializedName

data class CartContentDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("basket")
    val cart: List<CartUnitDto>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("total")
    val total: Int
) {
    fun toCartContent(): CartContent {
        return CartContent(
            id = id,
            cart = cart.map { it.toCartItem() },
            delivery = delivery,
            total = total
        )
    }
}