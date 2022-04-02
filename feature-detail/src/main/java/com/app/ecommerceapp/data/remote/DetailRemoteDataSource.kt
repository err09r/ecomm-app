package com.app.ecommerceapp.data.remote

import com.app.ecommerceapp.data.remote.dto.DetailContentDto
import retrofit2.Response

interface DetailRemoteDataSource {

    suspend fun loadDetailContent(): Response<DetailContentDto>
}