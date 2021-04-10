package com.picpay.desafio.android.feature.home.interactor.user

import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.domain.EntityResult
import kotlinx.coroutines.withContext

class GetUserListUseCase(private val dispatching: CoroutineDispatching, private val userGateway: UserGateway) {

    suspend operator fun invoke(): EntityResult<List<UserEntity>, GetUserListError> = withContext(dispatching.IO) {
        return@withContext EntityResult.Success(userGateway.getUserList())
    }
}