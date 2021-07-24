package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.dto.UserResponse

interface PicPayRepository {

    suspend fun getUsers() : List<UserResponse>
}