package com.picpay.desafio.android.remote.repository

import com.picpay.desafio.android.remote.model.User

interface DefaultRepository {

    fun getUsers(listener: ApiListener<List<User>>)
}