package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.constants.Constants.NULL_BODY_EXCEPTION
import com.app.ecommerceapp.data.api.ShopCartApi
import com.app.ecommerceapp.data.api.dto.mappers.CartContentMapper
import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val api: ShopCartApi) : CartRepository {

    override suspend fun getCartContent(): CartContent {
        val result =
            api.loadCartContent().body() ?: throw IllegalArgumentException(NULL_BODY_EXCEPTION)
        return CartContentMapper.mapToDomainModel(result)
    }
}