package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.data.repository.ContactRepository
import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

class ListContactsUseCaseImpl(
    private val contactRepository: ContactRepository
) : ListContactsUseCase {

    override suspend fun execute(): Flow<List<ContactModel>> =
        contactRepository.getContacts()

}