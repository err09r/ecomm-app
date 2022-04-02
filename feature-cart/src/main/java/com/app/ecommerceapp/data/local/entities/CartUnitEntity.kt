package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartUnitEntity(
    @PrimaryKey val id: Int,
    val src: String,
    val price: Int,
    val title: String,
    val amount: Int
)