package com.app.ecommerceapp.data.api

import com.app.ecommerceapp.data.api.dto.HomeContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopHomeApi {

    @GET("/home")
    suspend fun loadHomeData(): Response<HomeContentDto>
}