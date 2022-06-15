package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.response.User

interface PicPayRepository {
    suspend fun getUsers() : List<User>
}