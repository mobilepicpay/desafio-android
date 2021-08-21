package com.picpay.desafio.android.model

data class ErrorResponse (
    val code: Int,
    val massage: String
) : Throwable()