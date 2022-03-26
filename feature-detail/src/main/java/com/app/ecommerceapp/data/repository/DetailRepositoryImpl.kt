package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.constants.Constants.NULL_BODY_EXCEPTION
import com.app.ecommerceapp.data.api.ShopDetailApi
import com.app.ecommerceapp.data.api.dto.mappers.DetailContentMapper
import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.domain.repository.DetailRepository

class DetailRepositoryImpl(private val api: ShopDetailApi) : DetailRepository {

    override suspend fun getItemsDetails(): DetailContent {
        val result =
            api.loadItemDetails().body() ?: throw IllegalArgumentException(NULL_BODY_EXCEPTION)
        return DetailContentMapper.mapToDomainModel(result)
    }
}