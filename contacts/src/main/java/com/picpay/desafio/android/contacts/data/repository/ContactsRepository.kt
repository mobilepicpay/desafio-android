package com.picpay.desafio.android.contacts.data.repository

import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.data.network.ContactsService
import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<List<ContactResponse>>
}

class ContactsRepositoryImpl(
    private val service: ContactsService
) : ContactsRepository {

    override fun getContacts() =
        service
            .getContacts()
            .map { it.body()!! }
}