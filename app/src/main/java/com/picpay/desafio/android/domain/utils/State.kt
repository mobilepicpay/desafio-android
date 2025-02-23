package com.picpay.desafio.android.domain.utils

sealed class State<out T> {
    data object Loading : State<Nothing>()

    data class Success<T>(val data: T) : State<T>()

    data class Error(val error: ErrorInformation) : State<Nothing>()

    data class ErrorWithCache<T>(
        val data: T? = null,
        val error: ErrorInformation = ErrorInformation(
            "Não foi possível conectar ao servidor. Exibindo dados em cache."
        ),
    ) : State<T>()
}

data class ErrorInformation(
    val message: String? = null,
    val cause: Throwable? = null,
)

fun Exception.toErrorInformation(message: String? = null) = ErrorInformation(
    message = message
        ?: localizedMessage
        ?: "Ocorreu um erro. Tente novamente.",
    cause = this
)

interface StateBearer {
    val state: State<Any>
}