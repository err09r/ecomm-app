package com.app.ecommerceapp.domain.repository

import com.app.ecommerceapp.domain.models.CartContent

interface CartRepository {

    suspend fun getCartContent(): CartContent
}