package com.app.ecommerceapp.domain.util

interface HomeMapper<T, S, U> {

    fun mapToDomainModel(entity: U): S

    fun mapToEntity(dto: T): U
}