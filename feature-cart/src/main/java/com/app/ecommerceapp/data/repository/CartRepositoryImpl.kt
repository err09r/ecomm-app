package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.data.local.CartLocalDataSource
import com.app.ecommerceapp.data.mappers.CartContentMapper
import com.app.ecommerceapp.data.remote.CartRemoteDataSource
import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.repository.CartRepository
import com.app.ecommerceapp.util.constants.CommonConstants.NULL_BODY_EXCEPTION
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val remoteDataSource: CartRemoteDataSource,
    private val localDataSource: CartLocalDataSource
) : CartRepository {

    override suspend fun getCartContent(): CartContent {
        val localData = localDataSource.getCartContent()

        return if (localData.isNullOrEmpty()) {
            val remoteData =
                remoteDataSource.loadCartContent().body() ?: throw IllegalArgumentException(
                    NULL_BODY_EXCEPTION
                )

            val entity = CartContentMapper.mapToEntity(dto = remoteData)
            localDataSource.insertCartContent(entity)

            CartContentMapper.mapToDomainModel(entity = entity)
        } else {
            CartContentMapper.mapToDomainModel(entity = localData.first())
        }
    }
}