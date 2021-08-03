package com.picpay.android.user.usedatasoucer.network

import com.picpay.android.network.doRequest
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.UserRepository

class UserNetWorkRepository(private val picPayUserService: PicPayUserService) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return doRequest { picPayUserService.getUsers() }
    }

}