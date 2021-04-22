package com.picpay.desafio.android.feature_contacts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usescases.FetchUsersUseCase
import com.picpay.desafio.android.feature_contacts.mappers.toListPresentation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val usecase: FetchUsersUseCase
) : ViewModel() {

    private val interactions = Channel<UserInteraction>(Channel.UNLIMITED)
    private val states = MutableStateFlow<UsersScreenState>(UsersScreenState.Idle)

    fun bind() = states.asStateFlow()

    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().collect { interaction ->
                when (interaction) {
                    is UserInteraction.OpenedScreen, UserInteraction.RequestedFreshContent -> showUsers()
                }
            }
        }
    }

    fun handle(interaction: UserInteraction) {
        viewModelScope.launch {
            interactions.send(interaction)
        }
    }

    private suspend fun showUsers() {
        viewModelScope.launch {
            states.value = UsersScreenState.Loading
            try {
                val listUsers = usecase.execute().toListPresentation()
                if (!listUsers.isNullOrEmpty())
                states.value = UsersScreenState.Success(listUsers)
            } catch (error: Throwable) {
                states.value = UsersScreenState.Failed(error)
            }
        }
    }
}