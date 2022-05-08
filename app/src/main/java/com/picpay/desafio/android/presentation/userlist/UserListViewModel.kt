package com.picpay.desafio.android.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.di.IODispatcher
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import com.picpay.desafio.android.presentation.model.UserPresentable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class UserListViewModel @Inject constructor(
    private val userInteractor: UsersInteractor,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableScreenState = MutableStateFlow<UserListState>(UserListState.Loading)
    val screenState = mutableScreenState.asStateFlow()

    private val list = mutableListOf<UserPresentable>()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch(dispatcher) {
            mutableScreenState.value = UserListState.Loading
            userInteractor.getUsers().collect { response ->
                response.handleResult(
                    onSuccess = { users ->
                        list.addAll(users)
                        mutableScreenState.value = UserListState.Ready(list)
                    },
                    onError = {
                        mutableScreenState.value = UserListState.Error
                        print(it.toString())
                    }
                )
            }
        }
    }

    fun retryRequest() {
        getUsers()
    }

}
