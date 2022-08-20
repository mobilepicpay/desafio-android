package com.picpay.desafio.android.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.users.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase
) : ViewModel() {

    val uiState = UsersViewState()

    fun init() {
        viewModelScope.launch {
            getUsers().onSuccess { users ->
                uiState.users.postValue(users)
            }
        }
    }
}