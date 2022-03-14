package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.repository.model.UserLocal
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.repository.model.UserResponse

class UserListViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userResponseLiveData = MutableLiveData<UserResponse>(UserResponse.Loading)
    val liveData: LiveData<UserResponse> = _userResponseLiveData

    init {
        repository.getUsers(success = {
            _userResponseLiveData.postValue(UserResponse.Success)
        }, failure = {
            _userResponseLiveData.postValue(UserResponse.Failure(it))
        })
    }

    fun getUser(): LiveData<List<UserLocal>?> {
        return repository.getUserDao()
    }
}