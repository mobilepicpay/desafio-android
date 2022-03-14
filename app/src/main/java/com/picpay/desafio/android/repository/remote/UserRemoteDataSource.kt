package com.picpay.desafio.android.repository.remote

import com.picpay.desafio.android.repository.model.User

interface UserRemoteDataSource {

    fun getUser(success: (List<User>)-> Unit, failure: (String) -> Unit)
}