package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeContentEntity(
    @PrimaryKey val id: Int? = null,
    val hotItems: List<HotEntity>,
    val bestSellers: List<BestSellerEntity>
)