package com.picpay.android.user.usedatasoucer.local

import com.picpay.android.localdb.UserDao
import com.picpay.android.localdb.UserDb
import com.picpay.android.network.doRequest
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.UserRepository

class UserLocalRepository(private val userDb: UserDao) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return userDb
            .getUsers()
            .map {
                User(it.img, it.name, it.id.toInt(), it.username)
            }
    }

    suspend fun insertUsers(userList: List<User>) {
        userDb.insertUsers(userList.map { UserDb(it.id.toLong(), it.img, it.name, it.username) })
    }

}