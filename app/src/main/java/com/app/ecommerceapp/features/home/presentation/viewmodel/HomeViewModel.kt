package com.app.ecommerceapp.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.core.common.Resource
import com.app.ecommerceapp.core.common.UiState
import com.app.ecommerceapp.features.home.domain.models.HomeContent
import com.app.ecommerceapp.features.home.domain.usecases.GetHomeContentByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentByIdUseCase: GetHomeContentByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<HomeContent>>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = getHomeContentByIdUseCase()) {
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