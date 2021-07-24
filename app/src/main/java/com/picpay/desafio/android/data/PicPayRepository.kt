package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.source.local.UserDb
import kotlinx.coroutines.flow.Flow

interface PicPayRepository {

    fun getUsers(): Flow<Resource<List<UserDb>>>
}