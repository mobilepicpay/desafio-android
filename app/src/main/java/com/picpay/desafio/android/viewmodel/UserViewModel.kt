package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.repository.UserRepository

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

}