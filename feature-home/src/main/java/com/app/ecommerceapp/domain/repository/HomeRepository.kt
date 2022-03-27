package com.app.ecommerceapp.domain.repository

import com.app.ecommerceapp.domain.models.HomeContent

interface HomeRepository {

    suspend fun getHomeData(): HomeContent
}