package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.mapper.PicPayMapper
import com.picpay.desafio.android.data.database.PicPayDatabase
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.domain.repository.PicPayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PicPayRepositoryImpl(
    private val api: PicPayService,
    private val database: PicPayDatabase,
    private val mapper: PicPayMapper
) : PicPayRepository {

    override suspend fun insertUsersToLocal(users: List<User>): List<Long> {
        val userEntityList = withContext(Dispatchers.Default) {
            mapper.userToUserEntity(users)
        }
        return withContext(Dispatchers.IO) {
            database.userDao().insert(*userEntityList.toTypedArray())
        }
    }

    override suspend fun getUsersFromLocal(): List<User> {
        val local = withContext(Dispatchers.IO) {
            database.userDao().getUsers()
        }
        return withContext(Dispatchers.Default) {
            mapper.userEntityToUser(local)
        }
    }

    override suspend fun getUsersFromRemote(): List<User> {
        val remote = withContext(Dispatchers.IO) {
            api.getUsers()
        }
        return withContext(Dispatchers.Default) {
            mapper.userResponseToUser(remote)
        }
    }
}
