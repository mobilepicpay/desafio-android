package com.picpay.desafio.android.domain.errors

sealed class RemoteServiceError : Throwable() {

    object ClientOrigin : RemoteServiceError()
    object RemoteSystem : RemoteServiceError()
    object UnexpectedResponse : RemoteServiceError()

    override fun toString() =
        when (this) {
            ClientOrigin -> "Issue originated from client"
            RemoteSystem -> "Issue incoming from server"
            UnexpectedResponse -> "Broken contract"
        }
}

