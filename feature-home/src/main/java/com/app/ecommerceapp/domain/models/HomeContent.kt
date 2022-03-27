package com.app.ecommerceapp.domain.models

data class HomeContent(
    val hotItems: List<HotItem>,
    val bestSellers: List<BestSellerItem>
)