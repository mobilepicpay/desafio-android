package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.database.ContactsDao
import com.picpay.desafio.android.shared.model.ViewUser

/**
 * @author Vitor Herrmann on 13/07/2021
 */
interface SaveContactsUseCase {
    suspend fun saveContacts(contacts: List<ViewUser>)
}

class SaveContactsUseCaseImpl(
    private val contactsDao: ContactsDao
) : SaveContactsUseCase {

    override suspend fun saveContacts(contacts: List<ViewUser>) {
        contactsDao.insertAll(contacts)
    }
}
