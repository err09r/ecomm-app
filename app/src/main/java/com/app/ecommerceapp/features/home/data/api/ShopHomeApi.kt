package com.app.ecommerceapp.features.home.data.api

import com.app.ecommerceapp.features.home.data.api.dto.HomeContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopHomeApi {

    @GET("rest/home")
    suspend fun loadHomeData(): Response<List<HomeContentDto>>
}