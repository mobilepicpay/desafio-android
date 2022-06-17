package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.UserDataRepository

class GetUsersUC(private val repository:UserDataRepository) {
    suspend fun invoke() = repository.getUsers();
}