package com.app.ecommerceapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ecommerceapp.data.local.converters.CartConverters
import com.app.ecommerceapp.data.local.converters.DetailConverters
import com.app.ecommerceapp.data.local.converters.HomeConverters
import com.app.ecommerceapp.data.local.dao.CartDao
import com.app.ecommerceapp.data.local.dao.DetailDao
import com.app.ecommerceapp.data.local.dao.HomeDao
import com.app.ecommerceapp.data.local.entities.CartContentEntity
import com.app.ecommerceapp.data.local.entities.DetailContentEntity
import com.app.ecommerceapp.data.local.entities.HomeContentEntity

@Database(
    entities = [HomeContentEntity::class, DetailContentEntity::class, CartContentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(HomeConverters::class, DetailConverters::class, CartConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

    abstract fun detailDao(): DetailDao

    abstract fun cartDao(): CartDao
}