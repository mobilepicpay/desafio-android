package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val api: PicPayService,
    private val dao: UserDao
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            val cache = dao.getAllUsers().first()
            if (cache.isNotEmpty()) {
                cache
            } else {
                val users = api.getUsers()
                dao.insertUsers(users)
                users
            }
        }
    }
}
