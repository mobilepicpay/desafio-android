package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.local.toEntity
import com.picpay.desafio.android.data.local.toUser
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val service: PicPayService,
    private val dao: UserDao
) : UserRepository {

    override val users: Flow<List<User>> = dao.observeAll()
        .map { list -> list.map { it.toUser() } }

    override suspend fun refreshUsers() {
        val remote = service.getUsers()
        dao.clearAll()
        val entities = remote.mapNotNull { dto ->
            dto.toUser()?.toEntity()
        }

        dao.insertAll(entities)
    }
}
