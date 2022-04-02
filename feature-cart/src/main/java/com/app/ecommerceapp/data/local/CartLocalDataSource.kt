package com.app.ecommerceapp.data.local

import com.app.ecommerceapp.data.local.entities.CartContentEntity

interface CartLocalDataSource {

    fun getCartContent(): List<CartContentEntity>

    fun insertCartContent(entity: CartContentEntity)
}