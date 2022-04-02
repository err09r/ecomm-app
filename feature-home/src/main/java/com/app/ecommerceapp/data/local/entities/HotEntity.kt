package com.app.ecommerceapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HotEntity(
    @PrimaryKey val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val src: String,
    val isBuy: Boolean
)
