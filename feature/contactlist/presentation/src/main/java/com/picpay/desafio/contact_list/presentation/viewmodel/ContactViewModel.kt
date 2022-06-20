package com.picpay.desafio.contact_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.picpay.desafio.contact_list.presentation.model.mapper.UserDomainToUserUiMapper
import com.picpay.desafio.feature.contactlist.usecase.GetUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.HttpRetryException
import java.net.UnknownHostException

class ContactViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    userUiState: ContactUiState,
    private val mapper: UserDomainToUserUiMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(userUiState)
    val uiState = _uiState.asStateFlow()

    fun setUserEvent(userEvent: ContactUserEvent) {
        when (userEvent) {
            ContactUserEvent.OnInitScreen -> {
                handleInitScreen()
            }
        }
    }

    private fun handleInitScreen() {
        _uiState.value = uiState.value.copy(loading = true)
        viewModelScope.launch {
            getUsersUseCase().fold(onSuccess = { userList ->
                _uiState.value = uiState.value.copy(
                    userList = userList.map {
                        mapper.mapToUi(it)
                    }, loading = false
                )
            }, onFailure = {
                handleError(it)
            })
        }
    }

    private fun handleError(it: Throwable) {
        when (it) {
            is UnknownHostException -> _uiState.value = uiState.value.copy(
                loading = false,
                userList = null,
                error = ContactUiError.InternetConnection
            )
            else -> _uiState.value = uiState.value.copy(
                loading = false,
                userList = null,
                error = ContactUiError.UnknownError
            )
        }
    }
}