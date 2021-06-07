package com.picpay.desafio.android.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.entities.UsersDomain
import com.picpay.desafio.android.usecases.GetUsersUseCases
import com.picpay.desafio.android.utils.Event
import com.picpay.desafio.android.utils.exhaustive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val usersUseCases: GetUsersUseCases
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<Event<ViewUsersStates>>()
    val usersLiveData: LiveData<Event<ViewUsersStates>> = _usersLiveData

    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            usersUseCases.getUsers().collect {
                val result = when (it) {
                    is GetUsersUseCases.ResultUsers.UsersList -> ViewUsersStates.Show(
                        it.list
                    )
                    GetUsersUseCases.ResultUsers.NoUsers -> ViewUsersStates.Empty
                    GetUsersUseCases.ResultUsers.Error -> ViewUsersStates.Error
                }

                _usersLiveData.postValue(Event(result))
            }.exhaustive
        }
    }

    sealed class ViewUsersStates {
        data class Show(val list: List<UsersDomain>) : ViewUsersStates()
        object Empty : ViewUsersStates()
        object Error : ViewUsersStates()
    }
}