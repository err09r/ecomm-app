package com.app.ecommerceapp.features.detail.data.repository

import com.app.ecommerceapp.core.common.Constants.UNKNOWN_HOST_EXCEPTION
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.detail.data.api.ShopDetailApi
import com.app.ecommerceapp.features.detail.domain.models.DetailItem
import com.app.ecommerceapp.features.detail.domain.repository.DetailRepository
import retrofit2.HttpException
import java.net.UnknownHostException

class DetailRepositoryImpl(private val api: ShopDetailApi): DetailRepository {

    override suspend fun getItemsDetails(): Resource<List<DetailItem>> {
        return try {
            val result = api.loadItemDetails().body() ?: emptyList()
            Resource.Success(result.map { it.toDetailItem() })
        } catch(e: UnknownHostException) {
            Resource.Error(e.message ?: UNKNOWN_HOST_EXCEPTION)
        } catch(e: HttpException) {
            Resource.Error(e.message())
        }
    }
}