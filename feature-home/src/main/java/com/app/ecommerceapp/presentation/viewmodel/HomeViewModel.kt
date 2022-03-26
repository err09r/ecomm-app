package com.app.ecommerceapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.usecases.GetHomeContentByIdUseCase
import com.app.ecommerceapp.helpers.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentByIdUseCase: GetHomeContentByIdUseCase
) : ViewModel() {

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _uiState.value = UiState.Error(error = throwable.message ?: "")
    }

    private val _uiState = MutableStateFlow<UiState<HomeContent>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(handler) {
            val result = getHomeContentByIdUseCase()
            _uiState.value = UiState.Success(content = result)
        }
    }
}