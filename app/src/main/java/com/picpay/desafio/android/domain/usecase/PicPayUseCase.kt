package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.data.Resource
import com.picpay.desafio.android.data.mapper.UserResponseMapper

class PicPayUseCase(private val repository: PicPayRepository) {

    suspend operator fun invoke() =
        try {
            val users = UserResponseMapper.toUsersModel(response = repository.getUsers())
            Resource.Success(users)
        } catch (e: Exception) {
            Resource.Error("error todo")
        }

}