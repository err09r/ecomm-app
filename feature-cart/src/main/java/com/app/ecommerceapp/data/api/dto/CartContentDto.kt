package com.app.ecommerceapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class CartContentDto(
    @SerializedName("basket")
    val cart: List<CartUnitDto>,

    @SerializedName("delivery")
    val delivery: String,

    @SerializedName("total")
    val total: Int
)