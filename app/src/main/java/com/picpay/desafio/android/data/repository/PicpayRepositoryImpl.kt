package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.coreNetwork.models.Response
import com.picpay.desafio.android.coreNetwork.retrofit.client.NetworkManager
import com.picpay.desafio.android.data.datasource.PicpayLocalDataSource
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.model.mapToUser
import com.picpay.desafio.android.data.model.mapToUserEntity
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.domain.repository.PicpayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PicpayRepositoryImpl @Inject constructor(
    private val datasource: PicpayRemoteDatasource,
    private val localDataSource: PicpayLocalDataSource,
    private val manager: NetworkManager
) : PicpayRepository {

    override suspend fun getUsers(): Flow<Response<List<User>>> {
        return if (manager.isNetworkAvailable() && localDataSource.checkCacheExpired()) {

            datasource.getUsers().map { response ->

                if (response is Response.Success) {
                    localDataSource.saveUsers(
                        response.data.filter { user -> user.id != null }
                            .map { userData -> userData.mapToUserEntity() }
                    )
                }

                response.mapResponse { users ->
                    users
                        .filter { user -> user.id != null }
                        .map { userData -> userData.mapToUser() }
                }
            }
        } else {
            localDataSource.getUsers().map { response ->
                response.mapResponse { users ->
                    users.map { it.mapToUser() }
                }
            }
        }
    }

}
