package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.response.User
import com.picpay.desafio.android.data.service.PicPayService
import com.picpay.desafio.android.data.source.UserSource
import java.lang.Exception

class PicPayRepositoryImpl(private val picPayService: PicPayService): PicPayRepository {

    override suspend fun getUsers(): List<User> {
        return try {
            UserSource(picPayService).load()
        }catch (e: Exception){
            throw e
        }
    }
}