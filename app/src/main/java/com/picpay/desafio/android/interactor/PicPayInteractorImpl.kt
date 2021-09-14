package com.picpay.desafio.android.interactor

import com.picpay.desafio.android.mapper.UserMapper
import com.picpay.desafio.android.repository.PicPayRepository

class PicPayInteractorImpl(
    private val repository: PicPayRepository
) : PicPayInteractor {

    override suspend fun getUsers(): PicPayState.GetUsers {
        return try {
            val result = repository.getUsersFromRemote()
            if (result.isEmpty()) {
                PicPayState.GetUsers.Empty
            } else {
                val users = result.map { userResponse ->
                    UserMapper.toUser(userResponse)
                }
                PicPayState.GetUsers.Data(users)
            }
        } catch (e: Exception) {
            PicPayState.GetUsers.Error(e)
        }
    }
}
