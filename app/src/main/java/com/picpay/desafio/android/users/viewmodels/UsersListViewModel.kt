package com.picpay.desafio.android.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.common.LoadState
import com.picpay.desafio.android.users.repo.UsersApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersListViewModel(private val service: UsersApi) : ViewModel() {

    val usersListState = MutableLiveData<LoadState>(LoadState.READY)

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.getUsers()
            if(response.isSuccessful) {
                response.body()?.let {
                    usersListState.postValue(LoadState.SUCCESS(it))
                } ?: kotlin.run {
                    usersListState.postValue(LoadState.ERROR)
                }
            } else {
                usersListState.postValue(LoadState.ERROR)
            }
        }
    }
}