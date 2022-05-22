package com.picpay.desafio.android.data.contact

import com.picpay.desafio.android.data.contact.model.ContactDTO

class ContactRemoteDataSource(private val contactAPI: ContactAPI) {

    suspend fun fetchContacts(): Result<List<ContactDTO>> = runCatching {
        contactAPI.getContacts()
    }
}