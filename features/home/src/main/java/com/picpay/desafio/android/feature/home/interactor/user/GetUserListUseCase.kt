package com.picpay.desafio.android.feature.home.interactor.user

import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.domain.EntityResult
import com.picpay.desafio.android.shared.exception.NoInternetException
import com.picpay.desafio.android.shared.exception.UnknowServerException
import kotlinx.coroutines.withContext

class GetUserListUseCase(private val dispatching: CoroutineDispatching, private val userGateway: UserGateway) {

    sealed class GetUserListError {
        object NoInternetError : GetUserListError()
        object ServerError : GetUserListError()
    }

    suspend operator fun invoke(): EntityResult<List<UserEntity>, GetUserListError> = withContext(dispatching.IO) {
        return@withContext try {
            EntityResult.Success(userGateway.getUserList())
        } catch (exception: NoInternetException) {
            EntityResult.Error(GetUserListError.NoInternetError)
        } catch (exception: UnknowServerException) {
            EntityResult.Error(GetUserListError.ServerError)
        }
    }
}