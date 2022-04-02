package com.app.ecommerceapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerceapp.util.constants.CommonConstants.DEFAULT_ERROR_MSG
import com.app.ecommerceapp.domain.models.HomeContent
import com.app.ecommerceapp.domain.usecases.GetHomeContentUseCase
import com.app.ecommerceapp.util.helpers.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeContentUseCase: GetHomeContentUseCase
) : ViewModel() {

    private val coroutineContext: CoroutineContext by lazy {
        Dispatchers.IO + handler
    }

    private val handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _uiState.value = UiState.Error(error = throwable.message ?: DEFAULT_ERROR_MSG)
    }

    private val _uiState = MutableStateFlow<UiState<HomeContent>>(UiState.Loading())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(coroutineContext) {
            val result = getHomeContentUseCase()
            _uiState.value = UiState.Success(content = result)
        }
    }
}