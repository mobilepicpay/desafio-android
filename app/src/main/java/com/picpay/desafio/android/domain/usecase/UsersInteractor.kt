package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.presentation.model.UserPresentable
import kotlinx.coroutines.flow.Flow

internal interface UsersInteractor {
    suspend fun getUsers(): Flow<Response<List<UserPresentable>>>
}
