package com.app.ecommerceapp.features.cart.domain.usecases

import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.features.cart.domain.models.CartContent
import com.app.ecommerceapp.features.cart.domain.repository.CartRepository
import javax.inject.Inject

class GetCartContentByIdUseCase @Inject constructor(private val repository: CartRepository) {

    suspend fun execute(): Resource<List<CartContent>> {
        return repository.getCartContent()
    }
}