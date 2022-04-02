package com.app.ecommerceapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartContentDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("basket")
    val cart: List<CartUnitDto>,

    @SerializedName("delivery")
    val delivery: String,

    @SerializedName("total")
    val total: Int
)