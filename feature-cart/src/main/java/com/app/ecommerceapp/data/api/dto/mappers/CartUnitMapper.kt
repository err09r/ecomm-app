package com.app.ecommerceapp.data.api.dto.mappers

import com.app.ecommerceapp.data.api.dto.CartUnitDto
import com.app.ecommerceapp.domain.models.CartItem
import com.app.ecommerceapp.domain.utils.CartMapper

object CartUnitMapper : CartMapper<CartUnitDto, CartItem> {

    override fun mapToDomainModel(dto: CartUnitDto): CartItem {
        with(dto) {
            return CartItem(
                id = id,
                src = src,
                price = price,
                title = title,
                amount = amount
            )
        }
    }
}