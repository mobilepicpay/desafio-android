package com.picpay.desafio.android.core.data.repository.fake

import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.lists.fakeUserResponseList
import com.picpay.desafio.android.core.network.model.UserResponse

class FakeUserRemoteDataSource: UserRemoteDataSource {
    override suspend fun getUsers(): ApiResponse<List<UserResponse>> {
        return ApiResponse.Success(fakeUserResponseList)
    }
}