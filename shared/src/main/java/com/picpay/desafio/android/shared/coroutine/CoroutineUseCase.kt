package com.picpay.desafio.android.shared.coroutine

import com.picpay.desafio.android.shared.domain.EntityResult
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<REQUEST, RESULT>(private val dispatching: CoroutineDispatching) {

    protected abstract suspend fun onInvoke(request: REQUEST): EntityResult<RESULT>

    suspend operator fun invoke(request: REQUEST): EntityResult<RESULT> = withContext(dispatching.IO) {
        return@withContext onInvoke(request)
    }
}