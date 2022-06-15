package com.picpay.desafio.android.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.core.Resource
import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.domain.repository.GetUserCase
import kotlinx.coroutines.launch

class ListUsersViewModel(private val getUserCase: GetUserCase): ViewModel() {
    private val _users = MutableLiveData<Resource<List<User>>>()
    val allUsers: MutableLiveData<Resource<List<User>>>
        get() = _users

    init {
        getAllUsers()
    }

    fun clickRefresh(){
        getAllUsers()
    }

    private fun getAllUsers(){
        _users.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                val it = getUserCase.execute()
                _users.postValue(Resource.Success(it))
            }catch (e: Exception){
                _users.postValue(Resource.Error(e.message))
            }
        }
    }
}