package com.app.ecommerceapp.features.home.domain.usecases

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.home.domain.models.HomeContent
import com.app.ecommerceapp.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeContentByIdUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend fun execute(): Resource<List<HomeContent>> {
        return repository.getHomeData()
    }
}