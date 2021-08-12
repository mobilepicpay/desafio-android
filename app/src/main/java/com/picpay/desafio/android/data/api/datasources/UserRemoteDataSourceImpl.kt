package com.picpay.desafio.android.data.api.datasources

import com.picpay.desafio.android.data.api.mappers.UserRemoteMapper
import com.picpay.desafio.android.data.api.webservices.UserService
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.extensions.handleApiException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService,
    private val userRemoteMapper: UserRemoteMapper
) : UserRemoteDataSource {

    override suspend fun getUsers(): List<User> {
        return try {
            userService.getUsers().map { userResponse ->
                userRemoteMapper.fromResponseToModel(userResponse)
            }
        } catch (throwable: Throwable) {
            val apiException = throwable.handleApiException()
            throw apiException
        }
    }
}
