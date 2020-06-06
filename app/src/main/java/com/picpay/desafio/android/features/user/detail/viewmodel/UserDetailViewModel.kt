package com.picpay.desafio.android.features.user.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.custom.aliases.UserResult
import com.picpay.desafio.android.features.user.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val userLiveData = MutableLiveData<UserResult>()

    fun getUserLiveDate() = userLiveData

    fun getUser(userId: Int) {
        viewModelScope.launch {
            userRepository.getUser(userId).collect {
                userLiveData.value = it
            }
        }
    }
}