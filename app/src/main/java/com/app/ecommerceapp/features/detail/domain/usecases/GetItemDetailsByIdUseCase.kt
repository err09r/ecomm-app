package com.app.ecommerceapp.features.detail.domain.usecases

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.detail.domain.models.DetailItem
import com.app.ecommerceapp.features.detail.domain.repository.DetailRepository
import javax.inject.Inject

class GetItemDetailsByIdUseCase @Inject constructor(private val repository: DetailRepository) {

    suspend fun execute(): Resource<List<DetailItem>> {
        return repository.getItemsDetails()
    }
}