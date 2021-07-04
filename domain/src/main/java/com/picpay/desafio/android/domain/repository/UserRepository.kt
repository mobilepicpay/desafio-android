package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.entities.User
import com.picpay.desafio.android.domain.result.ResultWrapper

interface UserRepository {
    fun getUsers(): ResultWrapper<User>
}