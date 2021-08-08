package com.picpay.android.user.usedatasoucer.local

import com.picpay.android.localdb.UserDao
import com.picpay.android.localdb.UserDb
import com.picpay.android.network.CustomError
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.UserRepository

class UserLocalRepository(private val userDb: UserDao) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            userDb
                .getUsers()
                .map {
                    User(it.img, it.name, it.id.toInt(), it.username)
                }
        } catch (e: Exception) {
            throw CustomError(errorMessage = e.message?:"")
        }
    }

    suspend fun insertUsers(userList: List<User>) {
        userDb.insertUsers(userList.map { UserDb(it.id.toLong(), it.img, it.name, it.username) })
    }

    suspend fun deleteUsers() {
        userDb.deleteUsers()
    }


}