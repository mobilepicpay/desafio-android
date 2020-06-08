package com.picpay.desafio.data.mappers

import androidx.annotation.WorkerThread
import com.picpay.desafio.data.db.models.UserData
import com.picpay.desafio.data.models.UserResponse
import com.picpay.desafio.domain.models.User

class UserResponseToUserDataMapper(private val users: List<UserResponse>) : Mapper<List<UserData>> {

    @WorkerThread
    override fun getMapping(): List<UserData> {
        return users.asSequence().map {
            UserData(it.id, it.name, it.img, it.username)
        }.toList()
    }
}