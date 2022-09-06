package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.domain.model.ContactModel
import kotlinx.coroutines.flow.Flow

interface ListContactsUseCase {
    suspend fun execute(): Flow<List<ContactModel>>
}