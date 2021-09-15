package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse

interface PicPayRepository {

    suspend fun insertUsersToLocal(users: List<User>): List<Long>

    suspend fun getUsersFromLocal(): List<User>

    suspend fun getUsersFromRemote(): List<User>
}
