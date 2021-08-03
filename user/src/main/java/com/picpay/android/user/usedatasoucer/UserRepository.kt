package com.picpay.android.user.usedatasoucer


interface UserRepository {

   suspend fun getUsers(): List<User>

}