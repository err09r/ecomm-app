package com.app.ecommerceapp.domain.usecases

import com.app.ecommerceapp.domain.models.DetailContent
import com.app.ecommerceapp.domain.repository.DetailRepository
import javax.inject.Inject

class GetDetailContentUseCase @Inject constructor(private val repository: DetailRepository) {

    suspend operator fun invoke(): DetailContent {
        return repository.getDetailContent()
    }
}