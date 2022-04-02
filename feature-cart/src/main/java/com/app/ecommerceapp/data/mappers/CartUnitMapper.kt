package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.CartUnitEntity
import com.app.ecommerceapp.data.remote.dto.CartUnitDto
import com.app.ecommerceapp.domain.models.CartItem
import com.app.ecommerceapp.domain.util.CartMapper

object CartUnitMapper : CartMapper<CartUnitDto, CartItem, CartUnitEntity> {

    override fun mapToDomainModel(entity: CartUnitEntity): CartItem {
        with(entity) {
            return CartItem(
                id = id,
                src = src,
                price = price,
                title = title,
                amount = amount
            )
        }
    }

    override fun mapToEntity(dto: CartUnitDto): CartUnitEntity {
        with(dto) {
            return CartUnitEntity(
                id = id,
                src = src,
                price = price,
                title = title,
                amount = amount
            )
        }
    }
}