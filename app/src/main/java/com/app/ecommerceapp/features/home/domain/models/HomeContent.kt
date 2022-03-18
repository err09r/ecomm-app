package com.app.ecommerceapp.features.home.domain.models

data class HomeContent(
    val id: String,
    val hotItems: List<HotItem>,
    val bestSellers: List<BestSellerItem>
)