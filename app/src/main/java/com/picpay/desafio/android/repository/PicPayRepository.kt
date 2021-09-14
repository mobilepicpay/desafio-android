package com.picpay.desafio.android.repository

import com.picpay.desafio.android.data.UserResponse

interface PicPayRepository {

    suspend fun getUsersFromRemote(): List<UserResponse>
}
