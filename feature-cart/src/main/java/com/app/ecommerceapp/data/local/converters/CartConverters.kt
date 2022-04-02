package com.app.ecommerceapp.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.ecommerceapp.data.local.entities.CartUnitEntity
import com.app.ecommerceapp.util.extensions.fromJson
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class CartConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromCartUnitEntities(entities: List<CartUnitEntity>): String = gson.toJson(entities)

    @TypeConverter
    fun toCartUnitEntities(json: String): List<CartUnitEntity> = gson.fromJson(json)
}