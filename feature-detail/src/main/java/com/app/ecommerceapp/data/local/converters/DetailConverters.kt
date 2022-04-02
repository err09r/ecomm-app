package com.app.ecommerceapp.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.ecommerceapp.util.extensions.fromJson
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class DetailConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromList(list: List<String>): String = gson.toJson(list)

    @TypeConverter
    fun toList(json: String): List<String> = gson.fromJson(json)
}