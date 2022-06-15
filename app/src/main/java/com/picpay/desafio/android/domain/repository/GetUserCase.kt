package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.core.UseCase
import com.picpay.desafio.android.data.response.User

class GetUserCase(private val repository: PicPayRepository) : UseCase<List<User>>() {
    override suspend fun execute(): List<User> = repository.getUsers()
}