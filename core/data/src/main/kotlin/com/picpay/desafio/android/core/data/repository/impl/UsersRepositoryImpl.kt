package com.picpay.desafio.android.core.data.repository.impl

import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.core.data.mappers.toDomainModel
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.usersSync
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val imageProcessor: AppImageProcessor,
    private val ioDispatcher: CoroutineDispatcher,
): UsersRepository {

    override fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>> =
        localDataSource.searchUser(
            searchQuery = searchQuery,
            sortColumn = sortColumn,
            sortOrder = sortOrder
        )

    override fun getLocalUsers(): Flow<List<UserModel>>  =
        localDataSource.getUsers()

    override suspend fun insertLocalUser(user: UserResponse) =
        localDataSource.insertUser(user.toDomainModel(imageProcessor, ioDispatcher))

    override suspend fun getRemoteUsers() =
        remoteDataSource.getUsers()

    override suspend fun insertLocalUsers(vararg users: UserResponse) =
        localDataSource.insertUsers(*users.map { it.toDomainModel(imageProcessor, ioDispatcher) }.toTypedArray())

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.usersSync(
            ioDispatcher = ioDispatcher,
            usersFetcher = ::getRemoteUsers,
            usersPersistence = ::insertLocalUsers
        )

    private suspend fun insertLocalUsers(users: List<UserResponse>) =
        insertLocalUsers(*users.toTypedArray())
}