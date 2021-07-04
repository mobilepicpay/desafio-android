package com.picpay.desafio.android.remote.repository

interface ApiListener<T> {

    fun onSuccess(list: T)

    fun onFailure()
}