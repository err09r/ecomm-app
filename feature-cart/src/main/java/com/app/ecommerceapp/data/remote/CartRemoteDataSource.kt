package com.app.ecommerceapp.data.remote

import com.app.ecommerceapp.data.remote.dto.CartContentDto
import retrofit2.Response

interface CartRemoteDataSource {

    suspend fun loadCartContent(): Response<CartContentDto>
}