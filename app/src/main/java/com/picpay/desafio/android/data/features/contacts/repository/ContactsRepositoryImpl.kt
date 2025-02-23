package com.picpay.desafio.android.data.features.contacts.repository

import com.picpay.desafio.android.data.features.contacts.local.dao.ContactDao
import com.picpay.desafio.android.data.features.contacts.local.entities.database.toDomain
import com.picpay.desafio.android.data.features.contacts.model.toDb
import com.picpay.desafio.android.data.features.contacts.service.ContactsService
import com.picpay.desafio.android.data.utils.networkAdapter
import com.picpay.desafio.android.domain.features.contacts.model.Contact
import com.picpay.desafio.android.domain.features.contacts.repository.ContactsRepository
import com.picpay.desafio.android.domain.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactsRepositoryImpl(
    private val service: ContactsService,
    private val dao: ContactDao,
) : ContactsRepository {
    override suspend fun getContacts(endpoint: String): Flow<State<List<Contact>>> =
        networkAdapter(
            query = { dao.queryContacts().map { it.toDomain() } },
            get = { service.getContacts(endpoint) },
            saveGetResult = { listContactDb ->
                dao.deleteAll()
                dao.insertAll(listContactDb.toDb())
            },
        )
}

