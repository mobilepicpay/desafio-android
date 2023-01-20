package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.EspressoIdlingResource
import com.picpay.desafio.android.core.Outcome
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetAndUpdateUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel constructor(
    private val getUserUseCase: GetUsersUseCase,
    private val getAndUpdateUsersUseCase: GetAndUpdateUsersUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UserViewState>()
    val uiState: LiveData<UserViewState>
        get() = _uiState

    fun refresh() {
        viewModelScope.launch {
            getAndUpdateUsersUseCase.invoke().onStart {
                EspressoIdlingResource.increment()
                _uiState.value = UserViewState.Loading
            }.collect { result ->
                handleUseCaseOutcome(result)
                EspressoIdlingResource.decrement()
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            getUserUseCase.invoke().onStart {
                EspressoIdlingResource.increment()
                _uiState.value = UserViewState.Loading
            }.collect { result ->
                handleUseCaseOutcome(result)
                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun handleUseCaseOutcome(result: Outcome<List<User>>) {
        _uiState.value = when (result) {
            is Outcome.Success -> UserViewState.Success(result.data)
            is Outcome.Error -> {
                UserViewState.Error(result.error)
            }
        }
    }
}
