package com.app.ecommerceapp.data.api

import com.app.ecommerceapp.data.api.dto.CartContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopCartApi {

    @GET("/cart")
    suspend fun loadCartContent(): Response<CartContentDto>
}