package com.picpay.desafio.android.custom.errors

class NetworkException(message: String? = ErrorMessages.NETWORK_ERROR_MESSAGE) : Exception(message)

class NoDataException(message: String? = ErrorMessages.NO_SUCH_DATA) : Exception(message)

object ErrorMessages{
    const val NETWORK_ERROR_MESSAGE = "Não foi possível conectar com o servidor!"
    const val NO_SUCH_DATA = "Dados não encontrado na base de dados!"
    const val EMPTY_RESPONSE = "Servidor não retornou dados!"
    const val UNKNOWN_ERROR = "Um erro inesperado ocorreu!"
}