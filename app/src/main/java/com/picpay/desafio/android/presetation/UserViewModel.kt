package com.picpay.desafio.android.presetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.repository.UserServiceRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userServiceRepository: UserServiceRepository) : ViewModel() {

    fun getUsers() {
        viewModelScope.launch {
            userServiceRepository.getUsers()
        }

    }
}
