package com.app.ecommerceapp.domain.repository

import com.app.ecommerceapp.domain.models.DetailContent

interface DetailRepository {

    suspend fun getDetailContent(): DetailContent
}