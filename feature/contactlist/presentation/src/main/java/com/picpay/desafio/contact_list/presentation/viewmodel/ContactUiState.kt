package com.picpay.desafio.contact_list.presentation.viewmodel

import com.picpay.desafio.contact_list.presentation.model.UserUi

data class ContactUiState(
    val userList: List<UserUi>? = null,
    val loading: Boolean = false,
    val error: ContactUiError? = null
)