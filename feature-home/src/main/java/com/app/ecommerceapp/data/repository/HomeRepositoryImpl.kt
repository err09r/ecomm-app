package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.data.local.HomeLocalDataSource
import com.app.ecommerceapp.data.mappers.HomeContentMapper
import com.app.ecommerceapp.data.remote.HomeRemoteDataSource
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.repository.HomeRepository
import com.app.ecommerceapp.util.constants.CommonConstants.NULL_BODY_EXCEPTION
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource
) : HomeRepository {

    override suspend fun getHomeContent(): HomeContent {
        val localData = localDataSource.getHomeContent()

        return if (localData.isNullOrEmpty()) {
            val remoteData =
                remoteDataSource.loadHomeContent().body() ?: throw IllegalArgumentException(
                    NULL_BODY_EXCEPTION
                )

            val entity = HomeContentMapper.mapToEntity(dto = remoteData)
            localDataSource.insertHomeContent(entity)

            HomeContentMapper.mapToDomainModel(entity = entity)
        } else {
            HomeContentMapper.mapToDomainModel(entity = localData.first())
        }
    }
}