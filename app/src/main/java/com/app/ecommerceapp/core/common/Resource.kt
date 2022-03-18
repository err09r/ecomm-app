package com.app.ecommerceapp.core.common

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: String) : Resource<T>()
}