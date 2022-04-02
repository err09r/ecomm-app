package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailContentEntity(
    @PrimaryKey val id: Int? = null,
    val processor: String,
    val camera: String,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Float,
    val sd: String,
    val ram: String,
    val title: String,
    val images: List<String>,
    val colors: List<String>,
    val capacity: List<String>
)
