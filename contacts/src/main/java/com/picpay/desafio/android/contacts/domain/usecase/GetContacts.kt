package com.picpay.desafio.android.contacts.domain.usecase

import com.picpay.desafio.android.commons.mapper.Mapper
import com.picpay.desafio.android.contacts.data.model.ContactResponse
import com.picpay.desafio.android.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.contacts.domain.model.Contact
import io.reactivex.Single

interface GetContacts {
    fun execute(): Single<List<Contact>>
}

class GetContactsImpl(
    private val mapper: Mapper<ContactResponse, Contact>,
    private val repository: ContactsRepository
) : GetContacts {

    override fun execute() =
        repository
            .getContacts()
            .map { list ->
                list.map { mapper.transform(it) }
            }
}