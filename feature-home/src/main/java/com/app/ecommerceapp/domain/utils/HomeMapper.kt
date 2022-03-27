package com.app.ecommerceapp.domain.utils

interface HomeMapper<T, S> {
    fun mapToDomainModel(dto: T): S
}