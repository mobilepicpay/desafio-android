package com.picpay.desafio.android.domain.features.contacts.usecase

import com.picpay.desafio.android.domain.features.contacts.model.ContactsScreen
import com.picpay.desafio.android.domain.features.contacts.repository.ContactsRepository
import com.picpay.desafio.android.domain.utils.State
import com.picpay.desafio.android.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetContactsUseCase(
    private val repository: ContactsRepository
) : UseCase<String, ContactsScreen>() {
    override suspend fun execute(
        param: String,
        currentState: ContactsScreen,
    ): Flow<ContactsScreen> = flow {
        emit(currentState.copy(state = State.Loading))

        when (val result = repository.getContacts(param).first()) {
            is State.Success -> {
                emit(
                    currentState.copy(
                        state = State.Success(data = result.data),
                        contacts = result.data,
                    )
                )
            }

            is State.Error -> {
                emit(
                    currentState.copy(
                        state = State.Error(error = result.error),
                    )
                )
            }

            is State.ErrorWithCache -> {
                emit(
                    currentState.copy(
                        state = State.ErrorWithCache(data = result.data, error = result.error),
                        contacts = result.data,
                    )
                )
            }

            else -> {
                emit(currentState.copy(state = State.Loading))
            }
        }
    }
}