package com.picpay.desafio.android.feature.contacts.datasource.remote

import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.network.util.safeApiCall
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.network.model.UserResponse
import com.picpay.desafio.android.core.network.services.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserRemoteDataSourceImpl(
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRemoteDataSource {

    override suspend fun getUsers(): ApiResponse<List<UserResponse>> =
        safeApiCall(dispatcher) { userService.getUsers() }
            .let { apiResponse ->
                when (apiResponse) {

                    is ApiResponse.Error -> {
                        ApiResponse.Error(apiResponse.code, apiResponse.error)
                    }

                    is ApiResponse.Success -> {
                        ApiResponse.Success(apiResponse.value)
                    }
                }
            }

}