package com.picpay.desafio.android.feature.home.ui

import com.picpay.desafio.android.feature.home.interactor.user.GetUserListUseCase
import com.picpay.desafio.android.feature.home.interactor.user.UserEntity
import com.picpay.desafio.android.feature.home.ui.HomeViewModel.HomeViewEvent
import com.picpay.desafio.android.feature.home.ui.HomeViewModel.HomeViewState
import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.coroutine.CoroutineViewModel
import com.picpay.desafio.android.shared.domain.EntityResult
import kotlinx.coroutines.launch

internal class HomeViewModel(
    dispatching: CoroutineDispatching,
    private val getUserListUseCase: GetUserListUseCase
) : CoroutineViewModel<HomeViewState, HomeViewEvent>(dispatching) {

    sealed class HomeViewState {
        object Loading : HomeViewState()
        data class UserList(val list: List<UserEntity>) : HomeViewState()
        object Error : HomeViewState()
    }

    sealed class HomeViewEvent {
        object SendErrorToast : HomeViewEvent()
    }

    fun onCreate() {
        scope.launch {
            _state.value = HomeViewState.Loading

            getUserListUseCase().also {
                when (it) {
                    is EntityResult.Success -> {
                        _state.value = HomeViewState.UserList(it.value)
                    }

                    is EntityResult.Error -> {
                        _state.value = HomeViewState.Error
                        _event.value = HomeViewEvent.SendErrorToast
                    }

                }
            }
        }
    }
}