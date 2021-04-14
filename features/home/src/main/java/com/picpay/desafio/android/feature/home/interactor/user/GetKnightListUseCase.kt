package com.picpay.desafio.android.feature.home.interactor.user

import com.picpay.desafio.android.shared.coroutine.CoroutineService
import com.picpay.desafio.android.shared.domain.EntityResult
import com.picpay.desafio.android.shared.exception.NoInternetException
import com.picpay.desafio.android.shared.exception.UnknowServerException
import kotlinx.coroutines.withContext

class GetKnightListUseCase(private val service: CoroutineService, private val userGateway: UserGateway) {

    sealed class GetKnightListError {
        object NoInternetError : GetKnightListError()
        object ServerError : GetKnightListError()
    }

    suspend operator fun invoke(): EntityResult<List<UserEntity>, GetKnightListError> = withContext(service.IO) {
        return@withContext try {
            EntityResult.Success(userGateway.getUserList().toKnight())
        } catch (exception: NoInternetException) {
            EntityResult.Error(GetKnightListError.NoInternetError)
        } catch (exception: UnknowServerException) {
            EntityResult.Error(GetKnightListError.ServerError)
        }
    }

    private fun List<UserEntity>.toKnight(): List<UserEntity> {
        return map { UserEntity(it.id, it.username, "$KNIGHT_PREFIX ${it.name}", it.imageUrl) }
    }

    companion object {
        const val KNIGHT_PREFIX = "Sir"
    }
}