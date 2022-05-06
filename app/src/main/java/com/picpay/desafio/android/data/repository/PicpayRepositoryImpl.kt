package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.core_network.models.Response
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.mapToUser
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.PicpayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PicpayRepositoryImpl @Inject constructor(
    private val datasource: PicpayRemoteDatasource
) : PicpayRepository {

    override suspend fun getUsers(): Flow<Response<List<User>>> =
        datasource.getUsers().map { response ->
            response.mapResponse { users ->
                users.map { userData -> userData.mapToUser() }
            }
        }

}
