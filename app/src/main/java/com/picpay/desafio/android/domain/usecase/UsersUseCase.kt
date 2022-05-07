package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.domain.models.mapToUserPresentable
import com.picpay.desafio.android.domain.repository.PicpayRepository
import com.picpay.desafio.android.presentation.model.UserPresentable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UsersUseCase @Inject constructor(
    private val repository: PicpayRepository
) : UsersInteractor {

    override suspend fun getUsers(): Flow<Response<List<UserPresentable>>> {
        return repository.getUsers().map { response ->
            response.mapResponse { users ->
                users.map { user -> user.mapToUserPresentable() }
            }
        }
    }

}
