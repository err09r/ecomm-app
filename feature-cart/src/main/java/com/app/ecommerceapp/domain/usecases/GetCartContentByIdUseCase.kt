package com.app.ecommerceapp.domain.usecases

import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.repository.CartRepository
import javax.inject.Inject

class GetCartContentByIdUseCase @Inject constructor(private val repository: CartRepository) {

    suspend operator fun invoke(): CartContent {
        return repository.getCartContent()
    }
}