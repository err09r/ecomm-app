package com.app.ecommerceapp.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.core.common.UiState
import com.app.ecommerceapp.features.detail.domain.models.DetailItem
import com.app.ecommerceapp.features.detail.domain.usecases.GetItemDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getItemDetailsByIdUseCase: GetItemDetailsByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<DetailItem>>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = getItemDetailsByIdUseCase()) {
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