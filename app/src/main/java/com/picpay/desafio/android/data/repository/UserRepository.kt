package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.entity.User

interface ContactRepository {

    suspend fun getAll(): List<User>
}