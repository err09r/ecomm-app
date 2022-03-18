package com.app.ecommerceapp.features.detail.data.api

import com.app.ecommerceapp.features.detail.data.api.dto.DetailDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopDetailApi {

    @GET("/rest/detail")
    suspend fun loadItemDetails(): Response<List<DetailDto>>
}