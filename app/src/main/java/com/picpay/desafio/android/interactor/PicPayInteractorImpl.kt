package com.picpay.desafio.android.interactor

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
                    repository.insertUsersToLocal(remote)
                }

                remote
            } else {
                local
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
}
