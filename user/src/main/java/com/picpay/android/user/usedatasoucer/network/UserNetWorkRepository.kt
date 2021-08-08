package com.picpay.android.user.usedatasoucer.network

import com.picpay.android.network.doRequest
import com.picpay.android.user.usedatasoucer.User
import com.picpay.android.user.usedatasoucer.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserNetWorkRepository(
    private val picPayUserService: PicPayUserService,
    private val dispatcherTest: CoroutineDispatcher = Dispatchers.IO
    ) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return doRequest(dispatcher = dispatcherTest) { picPayUserService.getUsers() }
    }

}