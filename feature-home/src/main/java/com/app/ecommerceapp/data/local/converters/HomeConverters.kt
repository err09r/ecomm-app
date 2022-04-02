package com.app.ecommerceapp.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.ecommerceapp.data.local.entities.BestSellerEntity
import com.app.ecommerceapp.data.local.entities.HotEntity
import com.app.ecommerceapp.util.extensions.fromJson
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class HomeConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromHotEntities(entities: List<HotEntity>): String = gson.toJson(entities)

    @TypeConverter
    fun toHotEntities(json: String): List<HotEntity> = gson.fromJson(json)

    @TypeConverter
    fun fromBestSellerEntities(entities: List<BestSellerEntity>): String = gson.toJson(entities)

    @TypeConverter
    fun toBestSellerEntities(json: String): List<BestSellerEntity> = gson.fromJson(json)
}