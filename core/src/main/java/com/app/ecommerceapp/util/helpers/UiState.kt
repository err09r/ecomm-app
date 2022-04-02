package com.app.ecommerceapp.util.helpers

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    class Success<T>(val content: T) : UiState<T>()
    class Error<T>(val error: String) : UiState<T>()
}
