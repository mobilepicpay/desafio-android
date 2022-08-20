package com.picpay.desafio.android.users.data.repository

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.domain.model.UserError
import com.picpay.desafio.android.users.domain.model.User

interface UsersRepository {

    suspend fun getUsers(): Result<List<User>, UserError>
}
