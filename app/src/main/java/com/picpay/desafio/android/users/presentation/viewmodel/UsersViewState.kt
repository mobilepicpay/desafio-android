package com.picpay.desafio.android.users.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.picpay.desafio.android.users.domain.model.User
import javax.inject.Inject

class UsersViewState @Inject constructor() {

    val users = MutableLiveData<List<User>>()

    val state = MutableLiveData<State>()

    val isLoading = Transformations.map(state) { it == State.LOADING }
    val isError = Transformations.map(state) { it == State.ERROR }
    val shouldDisplayContent = Transformations.map(state) { it == State.SUCCESS }

    enum class State {
        LOADING, SUCCESS, ERROR
    }
}