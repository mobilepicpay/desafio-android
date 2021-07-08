package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.PicPayService

class FetchUseCase(private val service: PicPayService) {

     suspend fun execute(): Result<List<User>> {
         return try {
             Result.Success(service.getUsers())
         } catch (exception: Exception) {
             Result.Error(UseCaseError(""))
         }
    }
}