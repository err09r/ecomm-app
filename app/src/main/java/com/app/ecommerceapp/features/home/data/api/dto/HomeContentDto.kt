package com.app.ecommerceapp.features.home.data.api.dto

import com.app.ecommerceapp.features.home.domain.models.HomeContent
import com.google.gson.annotations.SerializedName

data class HomeContentDto(
    @SerializedName("_id")
    val id: String,
    @SerializedName("home_store")
    val hotItems: List<HotDto>,
    @SerializedName("best_seller")
    val bestSellers: List<BestSellerDto>
) {
    fun toHomeContent(): HomeContent {
        return HomeContent(
            id = id,
            hotItems = hotItems.map { it.toHotItem() },
            bestSellers = bestSellers.map { it.toBestSellerItem() },
        )
    }
}