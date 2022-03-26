package com.app.ecommerceapp.domain.utils

interface CartMapper<T, S> {
    fun mapToDomainModel(dto: T): S
}