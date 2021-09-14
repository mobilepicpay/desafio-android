package com.picpay.desafio.android.interactor

import com.picpay.desafio.android.data.UserResponse
import com.picpay.desafio.android.mapper.UserMapper
import com.picpay.desafio.android.repository.PicPayRepository

class PicPayInteractorImpl(
    private val repository: PicPayRepository
) : PicPayInteractor {

    override suspend fun getUsers(): PicPayState.GetUsers {
        return try {
            val local = repository.getUsersFromLocal()

            val users = if (local.isEmpty()) {
                val remote = repository.getUsersFromRemote()

                if (remote.isNotEmpty()) {
                    saveOnCache(remote)
                }

                repository.mapperUserResponseToUser(remote)
            } else {
                repository.mapperUserEntityToUser(local)
            }
            if (users.isEmpty()) {
                PicPayState.GetUsers.Empty
            } else {
                PicPayState.GetUsers.Data(users)
            }
        } catch (e: Exception) {
            PicPayState.GetUsers.Error(e)
        }
    }

    private suspend fun saveOnCache(remote: List<UserResponse>) {
        val userEntityList = repository.mapperUserResponseToUserEntity(remote)
        repository.insertUsersToLocal(userEntityList)
    }
}
