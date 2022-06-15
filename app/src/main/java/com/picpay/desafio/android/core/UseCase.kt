package com.picpay.desafio.android.core

abstract class UseCase<Source> {
    abstract suspend fun execute(): Source
}