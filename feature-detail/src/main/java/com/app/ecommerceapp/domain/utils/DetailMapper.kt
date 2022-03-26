package com.app.ecommerceapp.domain.utils

interface DetailMapper<T, S> {
    fun mapToDomainModel(dto: T): S
}