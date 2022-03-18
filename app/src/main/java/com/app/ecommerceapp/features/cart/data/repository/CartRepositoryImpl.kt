package com.app.ecommerceapp.features.cart.data.repository

import com.app.ecommerceapp.core.common.Constants.UNKNOWN_HOST_EXCEPTION
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.cart.data.api.ShopCartApi
import com.app.ecommerceapp.features.cart.domain.models.CartContent
import com.app.ecommerceapp.features.cart.domain.repository.CartRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val api: ShopCartApi) : CartRepository {

    override suspend fun getCartContent(): Resource<List<CartContent>> {
        return try {
            val result = api.loadCartContent().body() ?: emptyList()
            Resource.Success(result.map { it.toCartContent() })
        } catch (e: UnknownHostException) {
            Resource.Error(e.message ?: UNKNOWN_HOST_EXCEPTION)
        } catch (e: HttpException) {
            Resource.Error(e.message())
        }
    }
}