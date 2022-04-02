package com.app.ecommerceapp.data.mappers

import com.app.ecommerceapp.data.local.entities.CartContentEntity
import com.app.ecommerceapp.data.remote.dto.CartContentDto
import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.util.CartMapper

object CartContentMapper : CartMapper<CartContentDto, CartContent, CartContentEntity> {

    override fun mapToDomainModel(entity: CartContentEntity): CartContent {
        with(entity) {
            return CartContent(
                id = id,
                cart = cart.map { CartUnitMapper.mapToDomainModel(it) },
                delivery = delivery,
                total = total
            )
        }
    }

    override fun mapToEntity(dto: CartContentDto): CartContentEntity {
        with(dto) {
            return CartContentEntity(
                id = id,
                cart = cart.map { CartUnitMapper.mapToEntity(it) },
                delivery = delivery,
                total = total
            )
        }
    }
}