package com.picpay.desafio.android.features.user.list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.custom.aliases.UsersResult
import com.picpay.desafio.android.features.user.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val usersLiveData = MutableLiveData<UsersResult>()

    fun getUsersLiveDate() = usersLiveData

    init {
        viewModelScope.launch {
            userRepository.getUsers().collect { userResult ->
                usersLiveData.value = userResult
            }
        }
    }
}