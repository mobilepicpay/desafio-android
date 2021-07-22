package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.model.User

interface UserLocalDataSource {
    suspend fun getUserData(): List<User>
    suspend fun setUserLocal(userList: List<User>)
}