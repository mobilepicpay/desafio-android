package com.picpay.desafio.android.presenter.viewmodel

import com.picpay.desafio.android.domain.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow

sealed class ContactState {
    object Initial : ContactState()
    object Loading : ContactState()
    data class Success(val data: List<Contact>) : ContactState()
    object Error : ContactState()
}

fun MutableStateFlow<ContactState>.emitLoading() {
    value = ContactState.Loading
}

fun MutableStateFlow<ContactState>.emitSuccess(data: List<Contact>) {
    value = ContactState.Success(data)
}

fun MutableStateFlow<ContactState>.emitError() {
    value = ContactState.Error
}