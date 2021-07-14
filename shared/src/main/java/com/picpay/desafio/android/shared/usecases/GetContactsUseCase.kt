package com.picpay.desafio.android.shared.usecases

import com.picpay.desafio.android.shared.database.ContactsDao
import com.picpay.desafio.android.shared.model.ViewUser

/**
 * @author Vitor Herrmann on 13/07/2021
 */
interface GetContactsUseCase {
    suspend fun getContacts(): List<ViewUser>
}

class GetContactsUseCaseImpl(
    private val contactsDao: ContactsDao
) : GetContactsUseCase {

    override suspend fun getContacts() = contactsDao.getAll()
}
