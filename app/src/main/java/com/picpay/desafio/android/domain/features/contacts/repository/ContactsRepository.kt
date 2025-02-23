package com.picpay.desafio.android.domain.features.contacts.repository

import com.picpay.desafio.android.domain.features.contacts.model.Contact
import com.picpay.desafio.android.domain.utils.State
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    suspend fun getContacts(endpoint: String): Flow<State<List<Contact>>>
}