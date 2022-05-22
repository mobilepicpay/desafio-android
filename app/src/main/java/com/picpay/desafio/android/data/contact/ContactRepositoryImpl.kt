package com.picpay.desafio.android.data.contact

import com.picpay.desafio.android.data.contact.model.toContact
import com.picpay.desafio.android.domain.model.Contact
import com.picpay.desafio.android.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val remoteRepository: ContactRemoteDataSource
) : ContactRepository {

    override suspend fun getContacts(): Result<List<Contact>> = remoteRepository.fetchContacts()
        .mapCatching { contact ->
            contact.map { it.toContact() }
        }
}