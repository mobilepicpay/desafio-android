package com.picpay.desafio.data.mappers

import androidx.annotation.WorkerThread
import com.picpay.desafio.data.models.UserResponse
import com.picpay.desafio.domain.models.User

class UserResponseToUserMapper(private val users: List<UserResponse>) : Mapper<List<User>> {

    @WorkerThread
    override fun getMapping(): List<User> {
        return users.asSequence().map {
            User(it.id, it.name, it.img, it.username)
        }.toList()
    }
}