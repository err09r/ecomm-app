package com.app.ecommerceapp.domain.util

interface DetailMapper<T, S, U> {

    fun mapToDomainModel(entity: U): S

    fun mapToEntity(dto: T): U
}