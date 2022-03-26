package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.CartContentDto
import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.utils.CartMapper

object CartContentMapper : CartMapper<CartContentDto, CartContent> {

    override fun mapToDomainModel(dto: CartContentDto): CartContent {
        with(dto) {
            return CartContent(
                cart = cart.map { CartUnitMapper.mapToDomainModel(it) },
                delivery = delivery,
                total = total
            )
        }
    }
}