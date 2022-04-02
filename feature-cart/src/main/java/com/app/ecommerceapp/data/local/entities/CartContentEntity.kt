package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartContentEntity(
    @PrimaryKey val id: Int,
    val cart: List<CartUnitEntity>,
    val delivery: String,
    val total: Int
)
