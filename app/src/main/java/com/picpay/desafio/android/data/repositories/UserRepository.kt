package com.picpay.desafio.android.data.repositories

import com.picpay.desafio.android.OpenForTesting
import com.picpay.desafio.android.data.datasources.UserDataSourceInterface
import com.picpay.desafio.android.data.utils.Resource
import com.picpay.desafio.android.data.utils.networkBoundResource
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
@OpenForTesting
class UserRepository(private val dataSource: UserDataSourceInterface): UserRepositoryInterface {
    override fun getUsers(isRefresh: Boolean): Flow<Resource<List<User>>> {
        return networkBoundResource(
            fetchFromLocal = { dataSource.getFromLocal() },
            shouldFetchFromRemote = {  it == null },
            fetchFromRemote = { dataSource.getFromRemote() },
            processRemoteResponse = { },
            saveRemoteData = {
                dataSource.saveLocal(it)
            },
            onFetchFailed = { _, _ -> },
            isRefresh = isRefresh
        ).flowOn(Dispatchers.IO)
    }
}