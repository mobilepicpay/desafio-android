package com.picpay.desafio.android.data.source.remote

import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val api: PicPayApi) {

    suspend fun allUsers() = api.getUsers()

}