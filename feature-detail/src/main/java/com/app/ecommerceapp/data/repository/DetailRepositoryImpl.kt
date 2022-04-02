package com.app.ecommerceapp.data.repository

import com.app.ecommerceapp.data.local.DetailLocalDataSource
import com.app.ecommerceapp.data.mappers.DetailContentMapper
import com.app.ecommerceapp.data.remote.DetailRemoteDataSource
import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.domain.repository.DetailRepository
import com.app.ecommerceapp.util.constants.CommonConstants.NULL_BODY_EXCEPTION
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: DetailRemoteDataSource,
    private val localDataSource: DetailLocalDataSource
) : DetailRepository {

    override suspend fun getDetailContent(): DetailContent {
        val localData = localDataSource.getDetailContent()

        return if (localData.isNullOrEmpty()) {
            val remoteData =
                remoteDataSource.loadDetailContent().body() ?: throw IllegalArgumentException(
                    NULL_BODY_EXCEPTION
                )

            val entity = DetailContentMapper.mapToEntity(dto = remoteData)
            localDataSource.insertDetailContent(entity)

            DetailContentMapper.mapToDomainModel(entity = entity)
        } else {
            DetailContentMapper.mapToDomainModel(entity = localData.first())
        }
    }
}