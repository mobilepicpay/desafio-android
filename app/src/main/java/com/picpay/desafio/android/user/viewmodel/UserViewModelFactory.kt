package com.picpay.desafio.android.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picpay.desafio.android.user.usecase.UserUseCase

class UserViewModelFactory(private val useCase: UserUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserUseCase::class.java)
            .newInstance(useCase)
    }
}