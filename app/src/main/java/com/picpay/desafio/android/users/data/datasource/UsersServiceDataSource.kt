package com.picpay.desafio.android.users.data.datasource

import com.picpay.desafio.android.core.Result
import com.picpay.desafio.android.users.data.api.UsersApi
import com.picpay.desafio.android.users.data.mapper.UserResponseToModelMapper
import com.picpay.desafio.android.users.domain.model.UserError
import com.picpay.desafio.android.users.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersServiceDataSource @Inject constructor(
    private val usersApi: UsersApi,
    private val userResponseMapper: UserResponseToModelMapper
) : UsersRemoteDataSource {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getUsers(): Result<List<User>, UserError> {
        return try {
                val usersResult = usersApi.getUsers().map(userResponseMapper::mapFrom)
                Result.Success(usersResult)
            } catch (exception: Exception) {
                Result.Error(UserError)
            }

    }
}