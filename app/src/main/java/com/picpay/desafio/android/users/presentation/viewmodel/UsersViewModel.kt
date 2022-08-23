package com.picpay.desafio.android.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.users.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
    val viewState: UsersViewState = UsersViewState()
) : ViewModel() {

    fun init() {
        viewState.state.postValue(UsersViewState.State.LOADING)

        viewModelScope.launch {
            getUsers()
                .onSuccess { users ->
                    viewState.state.postValue(UsersViewState.State.SUCCESS)
                    viewState.users.postValue(users)
                }
                .onError {
                    viewState.state.postValue(UsersViewState.State.ERROR)
                }
        }
    }
}