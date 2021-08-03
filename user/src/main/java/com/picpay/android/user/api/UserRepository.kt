package com.picpay.android.user.api

import com.picpay.android.user.api.model.User

interface UserRepository {

   suspend fun getUsers(): List<User>

}