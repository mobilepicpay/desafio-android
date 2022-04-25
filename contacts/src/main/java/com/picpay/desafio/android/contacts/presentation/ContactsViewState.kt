package com.picpay.desafio.android.contacts.presentation

import com.picpay.desafio.android.contacts.domain.model.Contact

data class ContactsViewState(
    val contacts: List<Contact>,
    val isLoading: Boolean,
    val hasError: Boolean
)