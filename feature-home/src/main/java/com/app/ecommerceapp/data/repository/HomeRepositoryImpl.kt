package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.constants.CommonConstants.NULL_BODY_EXCEPTION
import com.app.ecommerceapp.data.api.ShopHomeApi
import com.app.ecommerceapp.data.api.dto.mappers.HomeContentMapper
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val api: ShopHomeApi) : HomeRepository {

    override suspend fun getHomeData(): HomeContent {
        val result = api.loadHomeData().body() ?: throw IllegalArgumentException(NULL_BODY_EXCEPTION)
        return HomeContentMapper.mapToDomainModel(result)
    }
}