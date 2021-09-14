package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.User

interface PicPayRepository {

    suspend fun getUsers(): List<User>
}
