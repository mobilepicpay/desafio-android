package com.picpay.desafio.android.commons.mapper

interface Mapper<I, O> {
    fun transform(input: I): O
}