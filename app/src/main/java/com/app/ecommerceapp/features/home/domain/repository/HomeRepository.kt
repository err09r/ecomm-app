package com.app.ecommerceapp.features.home.domain.repository

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.home.domain.models.HomeContent

interface HomeRepository {

    suspend fun getHomeData(): Resource<List<HomeContent>>
}