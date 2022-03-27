package com.app.ecommerceapp.data.api

import com.app.ecommerceapp.data.api.dto.DetailContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopDetailApi {

    @GET("/detail")
    suspend fun loadItemDetails(): Response<DetailContentDto>
}