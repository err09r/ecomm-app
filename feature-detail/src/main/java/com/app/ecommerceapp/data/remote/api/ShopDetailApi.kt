package com.app.ecommerceapp.data.remote.api

import com.app.ecommerceapp.data.remote.DetailRemoteDataSource
import com.app.ecommerceapp.data.remote.dto.DetailContentDto
import retrofit2.Response
import retrofit2.http.GET

interface ShopDetailApi: DetailRemoteDataSource {

    @GET("/detail")
    override suspend fun loadDetailContent(): Response<DetailContentDto>
}