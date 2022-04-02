package com.app.ecommerceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ecommerceapp.data.local.converters.HomeConverters
import com.app.ecommerceapp.data.local.dao.HomeDao
import com.app.ecommerceapp.data.local.entities.HomeContentEntity

@Database(entities = [HomeContentEntity::class], version = 1, exportSchema = false)
@TypeConverters(HomeConverters::class)
abstract class HomeDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao
}