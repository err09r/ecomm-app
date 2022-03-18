package com.app.ecommerceapp.features.cart.data.api.dto

import com.app.ecommerceapp.features.cart.domain.models.CartItem
import com.google.gson.annotations.SerializedName

data class CartUnitDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val src: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
) {
    fun toCartItem(): CartItem {
        return CartItem(
            id = id,
            src = src,
            price = price,
            title = title
        )
    }
}