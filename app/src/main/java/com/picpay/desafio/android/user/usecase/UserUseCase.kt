package com.picpay.desafio.android.user.usecase

import com.picpay.desafio.android.user.model.User

interface UserUseCase {

    suspend fun getUsers(): Result<List<User>>
}