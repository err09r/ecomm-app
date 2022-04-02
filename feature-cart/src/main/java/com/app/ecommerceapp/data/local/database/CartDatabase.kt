package com.app.ecommerceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ecommerceapp.data.local.converters.CartConverters
import com.app.ecommerceapp.data.local.dao.CartDao
import com.app.ecommerceapp.data.local.entities.CartContentEntity

@Database(entities = [CartContentEntity::class], version = 1, exportSchema = false)
@TypeConverters(CartConverters::class)
abstract class CartDatabase: RoomDatabase() {

    abstract fun cartDao(): CartDao
}