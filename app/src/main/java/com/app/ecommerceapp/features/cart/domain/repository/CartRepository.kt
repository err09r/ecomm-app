package com.app.ecommerceapp.features.cart.domain.repository

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.cart.domain.models.CartContent

interface CartRepository {

    suspend fun getCartContent(): Resource<List<CartContent>>
}