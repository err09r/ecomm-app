package com.app.ecommerceapp.features.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.core.common.UiState
import com.app.ecommerceapp.features.cart.domain.models.CartContent
import com.app.ecommerceapp.features.cart.domain.usecases.GetCartContentByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartContentByIdUseCase: GetCartContentByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<CartContent>>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = getCartContentByIdUseCase.execute()) {
                is Resource.Success -> {
                    _uiState.emit(UiState.Success(result.data))
                }
                is Resource.Error -> {
                    _uiState.emit(UiState.Error(result.error))
                }
            }
        }
    }
}