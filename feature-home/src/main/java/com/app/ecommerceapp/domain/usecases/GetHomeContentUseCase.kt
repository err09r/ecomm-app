package com.app.ecommerceapp.domain.usecases

import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeContentUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke(): HomeContent {
        return repository.getHomeContent()
    }
}