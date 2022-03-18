package com.app.ecommerceapp.features.detail.domain.repository

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.detail.domain.models.DetailItem

interface DetailRepository {

    suspend fun getItemsDetails(): Resource<List<DetailItem>>
}