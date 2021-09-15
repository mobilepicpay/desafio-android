package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.model.User

interface PicPayRepository {

    suspend fun insertUsersToLocal(users: List<User>): List<Long>

    suspend fun getUsersFromLocal(): List<User>

    suspend fun getUsersFromRemote(): List<User>
}
