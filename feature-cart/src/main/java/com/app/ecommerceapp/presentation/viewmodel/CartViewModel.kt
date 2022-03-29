package com.app.ecommerceapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.constants.CommonConstants.DEFAULT_ERROR_MSG
import com.app.ecommerceapp.domain.models.CartContent
import com.app.ecommerceapp.domain.usecases.GetCartContentByIdUseCase
import com.app.ecommerceapp.helpers.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartContentByIdUseCase: GetCartContentByIdUseCase
) : ViewModel() {

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _uiState.value = UiState.Error(error = throwable.message ?: DEFAULT_ERROR_MSG)
    }

    private val _uiState = MutableStateFlow<UiState<CartContent>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(handler) {
            val result = getCartContentByIdUseCase()
            _uiState.value = UiState.Success(content = result)
        }
    }
}