package com.picpay.desafio.android.contacts.presentation

import com.picpay.desafio.android.contacts.domain.model.Contact

sealed class ContactsViewState {
    data class ShowContacts(val contacts: List<Contact>) : ContactsViewState()
    data class ToggleLoading(val isLoading: Boolean) : ContactsViewState()
    object ShowError : ContactsViewState()
}