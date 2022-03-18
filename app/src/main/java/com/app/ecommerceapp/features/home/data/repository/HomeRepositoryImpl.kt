package com.app.ecommerceapp.features.home.data.repository

import com.app.ecommerceapp.core.common.Constants.UNKNOWN_HOST_EXCEPTION
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.home.data.api.ShopHomeApi
import com.app.ecommerceapp.features.home.domain.models.HomeContent
import com.app.ecommerceapp.features.home.domain.repository.HomeRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val api: ShopHomeApi) : HomeRepository {

    override suspend fun getHomeData(): Resource<List<HomeContent>> {
        return try {
            val result = api.loadHomeData().body() ?: emptyList()
            Resource.Success(result.map { it.toHomeContent() })
        } catch (e: UnknownHostException) {
            Resource.Error(e.message ?: UNKNOWN_HOST_EXCEPTION)
        } catch (e: HttpException) {
            Resource.Error(e.message())
        }
    }
}