package com.app.ecommerceapp.features.home.data.api.dto

import com.app.ecommerceapp.features.home.domain.models.BestSellerItem
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
) {
    fun toBestSellerItem(): BestSellerItem {
        return BestSellerItem(
            id = id,
            fullPrice = fullPrice,
            finalPrice = finalPrice,
            title = title,
            src = src,
            isFavorites = isFavorites
        )
    }
}
