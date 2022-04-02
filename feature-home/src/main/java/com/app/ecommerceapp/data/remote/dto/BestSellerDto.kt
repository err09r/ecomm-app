package com.app.ecommerceapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BestSellerDto(
    
    @SerializedName("id")
    val id: Int,

    @SerializedName("price_without_discount")
    val fullPrice: Int,

    @SerializedName("discount_price")
    val finalPrice: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("picture")
    val src: String,

    @SerializedName("is_favorites")
    val isFavorites: Boolean
)
