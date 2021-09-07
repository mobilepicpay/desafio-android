package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayService

class Repository {
    private val api = PicPayService.instance

    fun getUsers() = api.getUsers()
}
