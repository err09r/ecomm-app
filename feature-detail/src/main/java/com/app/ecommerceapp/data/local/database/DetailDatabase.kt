package com.app.ecommerceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ecommerceapp.data.local.converters.DetailConverters
import com.app.ecommerceapp.data.local.dao.DetailDao
import com.app.ecommerceapp.data.local.entities.DetailContentEntity

@Database(entities = [DetailContentEntity::class], version = 1, exportSchema = false)
@TypeConverters(DetailConverters::class)
abstract class DetailDatabase : RoomDatabase() {

    abstract fun detailDao(): DetailDao
}