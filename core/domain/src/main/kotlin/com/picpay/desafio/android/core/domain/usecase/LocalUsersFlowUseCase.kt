package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LocalUsersFlowUseCase {
    operator fun invoke(): Flow<List<UserModel>>
}
