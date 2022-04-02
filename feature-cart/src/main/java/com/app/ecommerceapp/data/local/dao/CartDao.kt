package com.app.ecommerceapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.ecommerceapp.data.local.CartLocalDataSource
import com.app.ecommerceapp.data.local.entities.CartContentEntity

@Dao
interface CartDao: CartLocalDataSource {

    @Query("SELECT * FROM cartcontententity")
    override fun getCartContent(): List<CartContentEntity>

    @Insert
    override fun insertCartContent(entity: CartContentEntity)
}