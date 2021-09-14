package com.picpay.desafio.android.interactor

import com.picpay.desafio.android.repository.PicPayRepository

class PicPayInteractorImpl(
    private val repository: PicPayRepository
) : PicPayInteractor {

    override suspend fun getUsers(): PicPayState.GetUsers {
        return try {
            val result = repository.getUsers()
            if (result.isEmpty()) {
                PicPayState.GetUsers.Empty
            } else {
                PicPayState.GetUsers.Data(result)
            }
        } catch (e: Exception) {
            PicPayState.GetUsers.Error(e)
        }
    }
}
