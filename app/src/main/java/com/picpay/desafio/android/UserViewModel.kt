package com.picpay.desafio.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.model.UserResponse

class UserViewModel : ViewModel() {

    private val users: MutableLiveData<List<UserResponse>> by lazy {
        MutableLiveData<List<UserResponse>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<UserResponse>> {
        return users
    }

    private fun loadUsers() {

    }

}