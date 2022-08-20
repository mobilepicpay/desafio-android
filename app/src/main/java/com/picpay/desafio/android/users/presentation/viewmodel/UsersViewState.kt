package com.picpay.desafio.android.users.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.users.domain.model.User

class UsersViewState {

    val users = MutableLiveData<List<User>>()
}