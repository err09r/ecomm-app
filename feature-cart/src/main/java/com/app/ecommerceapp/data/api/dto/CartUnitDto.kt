package com.app.ecommerceapp.data.api.dto

import com.google.gson.annotations.SerializedName

data class CartUnitDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val src: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("amount")
    val amount: Int,
)