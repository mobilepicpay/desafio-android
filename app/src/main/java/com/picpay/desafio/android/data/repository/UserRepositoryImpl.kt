package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(private val service: PicPayService) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return try {
            service.getUsers().toDomain()
        } catch (e: Exception) {
            throw Exception("Erro ao buscar usuários: ${e.message}")
        }
    }
}
