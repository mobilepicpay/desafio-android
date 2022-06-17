package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.domain.model.ContactModel
import com.picpay.desafio.android.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ListContactsUseCaseImpl(private val contactRepository: ContactRepository) : ListContactsUseCase {
    override fun invoke(): Flow<List<ContactModel>> = contactRepository.getContacts()
}