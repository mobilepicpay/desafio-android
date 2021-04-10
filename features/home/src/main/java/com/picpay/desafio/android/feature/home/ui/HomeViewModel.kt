package com.picpay.desafio.android.feature.home.ui

import com.picpay.desafio.android.feature.home.ui.HomeViewModel.Event
import com.picpay.desafio.android.feature.home.ui.HomeViewModel.State
import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.coroutine.CoroutineViewModel

internal class HomeViewModel(dispatching: CoroutineDispatching) : CoroutineViewModel<State, Event>(dispatching) {
    sealed class State {
        object Loading : State()
        object LoadUserList : State()
        object Error : State()
    }

    sealed class Event

    fun onCreate() {
        // TODO carregar a lista de usuarios
    }
}