package com.app.ecommerceapp.data.local

import com.app.ecommerceapp.data.local.entities.HomeContentEntity

interface HomeLocalDataSource {

    fun getHomeContent(): List<HomeContentEntity>

    fun insertHomeContent(entity: HomeContentEntity)
}