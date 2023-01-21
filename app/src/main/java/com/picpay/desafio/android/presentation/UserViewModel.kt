package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel constructor(
    private val getUserUseCase: GetUsersUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UserViewState>()
    val uiState: LiveData<UserViewState>
        get() = _uiState

    fun getUsers(isRefresh: Boolean) {
        viewModelScope.launch {
            getUserUseCase.invoke(GetUsersUseCase.Request(!isRefresh)).onStart {
                _uiState.value = UserViewState.Loading
            }.collect { result ->
                _uiState.value = when (result) {
                    is Outcome.Success -> UserViewState.Success(result.data)
                    is Outcome.Error -> {
                        UserViewState.Error(result.error)
                    }
                }
            }
        }
    }
}
