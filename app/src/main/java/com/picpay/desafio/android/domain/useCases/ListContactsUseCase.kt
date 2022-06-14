package com.picpay.desafio.android.domain.useCases

import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ListContactsUseCase {
    operator fun invoke(): Flow<List<UserModel>>
}