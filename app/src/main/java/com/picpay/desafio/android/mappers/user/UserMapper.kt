package com.picpay.desafio.android.mappers.user

import androidx.annotation.WorkerThread
import com.picpay.desafio.android.custom.aliases.ListOfUsers
import com.picpay.desafio.android.mappers.Mapper
import com.picpay.desafio.android.network.response.UserResponse
import com.picpay.desafio.android.room.models.User

class UserMapper(private val apiUser: List<UserResponse>) : Mapper<ListOfUsers> {

    @WorkerThread
    override fun getMapping(): ListOfUsers {
        return apiUser.asSequence().map {
            User(it.id, it.name, it.img, it.username)
        }.toList()
    }
}