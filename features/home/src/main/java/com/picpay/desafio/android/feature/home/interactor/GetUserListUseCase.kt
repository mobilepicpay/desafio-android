package com.picpay.desafio.android.feature.home.interactor

import com.picpay.desafio.android.feature.home.entity.UserEntity
import com.picpay.desafio.android.feature.home.gateway.UserGateway
import com.picpay.desafio.android.feature.home.interactor.GetUserListUseCase.Request
import com.picpay.desafio.android.feature.home.interactor.GetUserListUseCase.Response
import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.coroutine.CoroutineUseCase
import com.picpay.desafio.android.shared.domain.EntityError
import com.picpay.desafio.android.shared.domain.EntityResult

class GetUserListUseCase(dispatching: CoroutineDispatching, private val userGateway: UserGateway) :
    CoroutineUseCase<Request, Response>(dispatching) {
    object Request

    data class Response(val list: List<UserEntity>)

    override suspend fun onInvoke(request: Request): EntityResult<Response> {
        return try {
            EntityResult.Success(Response(userGateway.getUserList()))
        } catch (e: Exception) {
            EntityResult.Error(EntityError.ServerError)
        }
    }
}