package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.repository.local.UserLocalDataSource
import com.picpay.desafio.android.repository.model.UserLocal
import com.picpay.desafio.android.repository.remote.UserRemoteDataSource

class UserRepositoryImp(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUsers(success: () -> Unit, failure: (String) -> Unit) {

        userRemoteDataSource.getUser(success = { users ->
            userLocalDataSource.insert(users)
            success()
        }, failure = failure)

    }

    override fun getUserDao(): LiveData<List<UserLocal>?> {
        return userLocalDataSource.getUser()
    }
}