package com.picpay.android.user.api

import com.picpay.android.network.doRequest
import com.picpay.android.user.api.model.User

class UserNetWorkRepository(private val picPayUserService: PicPayUserService) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return doRequest { picPayUserService.getUsers() }
    }

}