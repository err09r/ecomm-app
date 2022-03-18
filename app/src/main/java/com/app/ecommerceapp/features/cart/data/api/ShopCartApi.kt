package com.app.ecommerceapp.features.cart.data.api

import com.app.ecommerceapp.features.cart.data.api.dto.CartContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopCartApi {

    @GET("rest/cart")
    suspend fun loadCartContent(): Response<List<CartContentDto>>
}