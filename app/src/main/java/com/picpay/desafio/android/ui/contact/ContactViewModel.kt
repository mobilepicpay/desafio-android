package com.picpay.desafio.android.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.entity.User
import com.picpay.desafio.android.data.repository.UserRepository
import kotlinx.coroutines.launch

class ContactViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    fun fetchContacts() {
        viewModelScope.launch {
            _users.postValue(userRepository.getAll())
        }
    }
}