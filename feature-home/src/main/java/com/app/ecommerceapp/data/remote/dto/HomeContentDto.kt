package com.app.ecommerceapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HomeContentDto(

    @SerializedName("home_store")
    val hotItems: List<HotDto>,

    @SerializedName("best_seller")
    val bestSellers: List<BestSellerDto>
)