package com.app.ecommerceapp.data.remote.api

import com.app.ecommerceapp.data.remote.HomeRemoteDataSource
import com.app.ecommerceapp.data.remote.dto.HomeContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopHomeApi : HomeRemoteDataSource {

    @GET("/home")
    override suspend fun loadHomeContent(): Response<HomeContentDto>
}