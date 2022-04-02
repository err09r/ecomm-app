package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BestSellerEntity(
    @PrimaryKey val id: Int,
    val fullPrice: Int,
    val finalPrice: Int,
    val title: String,
    val src: String,
    val isFavorites: Boolean
)
