package com.app.ecommerceapp.data.remote.api

import com.app.ecommerceapp.data.remote.CartRemoteDataSource
import com.app.ecommerceapp.data.remote.dto.CartContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopCartApi: CartRemoteDataSource {

    @GET("/cart")
    override suspend fun loadCartContent(): Response<CartContentDto>
}