package com.app.ecommerceapp.data.remote

import com.app.ecommerceapp.data.remote.dto.HomeContentDto
import retrofit2.Response

interface HomeRemoteDataSource {

    suspend fun loadHomeContent(): Response<HomeContentDto>
}