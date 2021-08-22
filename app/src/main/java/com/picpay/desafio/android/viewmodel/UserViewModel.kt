package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.picpay.desafio.android.repository.UserRepository

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    val listUsers = repository.getUsers().asLiveData()
}