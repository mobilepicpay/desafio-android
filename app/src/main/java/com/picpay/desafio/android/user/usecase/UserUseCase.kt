package com.picpay.desafio.android.user.usecase

import com.picpay.desafio.android.user.model.ResultUser
import com.picpay.desafio.android.user.model.User
import com.picpay.desafio.android.user.repository.UserRepository

class UserUseCase(private val repository: UserRepository) {
    suspend fun getContacts(): ResultUser<List<User>> {
        return try {
            val response = repository.getContactsLocal()
            val data = mutableListOf<User>()
            if (response.isNotEmpty()) {
                data.addAll(response)
            } else {
                data.addAll(repository.getContactsRemote())
            }
            ResultUser(data = data)
        } catch (e: Exception) {
            ResultUser(isSuccess = false, messageError = e.message.toString(), data = arrayListOf())
        }
    }
}