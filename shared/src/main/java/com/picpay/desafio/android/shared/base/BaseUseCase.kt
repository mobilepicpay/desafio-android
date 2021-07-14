package com.picpay.desafio.android.shared.base

import com.picpay.desafio.android.shared.model.ResultWrapper

/**
 * @author Vitor Herrmann on 12/07/2021
 */
abstract class BaseUseCase {

    suspend fun <T> request(apiCall: suspend () -> T): ResultWrapper<T> {
        return try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            ResultWrapper.Error(throwable)
        }
    }
}
