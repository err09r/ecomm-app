package com.app.ecommerceapp.data.local

import com.app.ecommerceapp.data.local.entities.DetailContentEntity

interface DetailLocalDataSource {

    fun getDetailContent(): List<DetailContentEntity>

    fun insertDetailContent(entity: DetailContentEntity)
}