package com.app.ecommerceapp.core.common

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    class Success<T>(val content: T) : UiState<T>()
    class Error<T>(val error: String) : UiState<T>()
}
