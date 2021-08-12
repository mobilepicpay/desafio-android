package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.data.api.datasources.UserRemoteDataSource
import com.picpay.desafio.android.data.db.datasources.UserLocalDataSource
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.utils.sealeds.DataConsumptionStatus
import com.picpay.desafio.android.utils.sealeds.DataErrorException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
    private val dataRequestManager: DataRequestManager
) : UserRepository {

    override suspend fun getUsers(): Flow<DataConsumptionStatus<List<User>, DataErrorException>> {
        return dataRequestManager.performDataRequestManagement(
            loadLocalData = { userLocalDataSource.getUsers() },
            shouldFetchData = { shouldFetch() },
            onFetchSucceeded = {
                val fetchedUsers = userRemoteDataSource.getUsers()
                userLocalDataSource.insertUsers(fetchedUsers)
                userLocalDataSource.getUsers()
            }
        )
    }

    override suspend fun refreshUsers(): Flow<DataConsumptionStatus<List<User>, DataErrorException>> {
        return dataRequestManager.performDataRequestManagement(
            shouldFetchData = { true },
            onFetchSucceeded = {
                val fetchedUsers = userRemoteDataSource.getUsers()
                userLocalDataSource.insertUsers(fetchedUsers)
                userLocalDataSource.getUsers()
            }
        )
    }

    private fun shouldFetch(): Boolean {
        // TODO add some business logic to not fetch the users every time. It could be each every
        //  X minutes or something like that.
        return true
    }
}
