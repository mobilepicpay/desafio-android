package com.picpay.desafio.repository

import com.picpay.desafio.api.RetrofitInstance
import com.picpay.desafio.db.ContactDatabase
import com.picpay.desafio.models.Contact

class ContactRepository(val db: ContactDatabase) {

    suspend fun getContacts() = RetrofitInstance.api.getContactList()

    suspend fun insert(contact: Contact) = db.getContactDao().insert(contact)

    fun getFavoriteContacts() = db.getContactDao().getAllContacts()

    suspend fun deleteContact(contact: Contact) = db.getContactDao().deleteContact(contact)
}