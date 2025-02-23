package com.picpay.desafio.android.data.utils

import com.picpay.desafio.android.domain.utils.State
import com.picpay.desafio.android.domain.utils.toErrorInformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <Result, Request> networkAdapter(
    crossinline query: () -> Flow<Result>,
    crossinline get: suspend () -> Request,
    crossinline saveGetResult: suspend (Request) -> Unit,
): Flow<State<Result>> = flow {
    var data = query().first()

    try {
        saveGetResult(get())
        data = query().first()

    } catch (error: Exception) {
        if (data != emptyList<Result>() && data != null) {
            emit(
                State.ErrorWithCache(
                    data,
                    error.toErrorInformation(
                        "Não foi possível conectar ao servidor. Exibindo dados em cache."
                    ),
                )
            )
        } else {
            emit(
                State.Error(
                    error = error.toErrorInformation(
                        "Não foi possível conectar ao servidor."
                    ),
                )
            )
        }
    }
    emit(State.Success(data))
}