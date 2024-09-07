package com.picpay.desafio.android.core.data.repository.fake

import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageProcessor
import com.picpay.desafio.android.core.model.UserModel
import com.picpay.desafio.android.core.data.mappers.toDomainModel
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.usersSync
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.TestDispatcher

class FakeUsersRepository(
    private val remoteDataSource: FakeUserRemoteDataSource,
    private val localDataSource: FakeUserLocalDataSource,
    private val imageProcessor: FakeAppImageProcessor,
    private val ioDispatcher: TestDispatcher,
    private val testSynchronizer: Synchronizer
): UsersRepository {
    override fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>> =
        localDataSource.searchUser(searchQuery, sortColumn, sortOrder)

    override fun getLocalUsers(): Flow<List<UserModel>> =
        localDataSource.getUsers()

    override suspend fun getRemoteUsers(): ApiResponse<List<UserResponse>> =
        remoteDataSource.getUsers()

    override suspend fun insertLocalUser(user: UserResponse) =
        localDataSource.insertUser(user.toDomainModel(imageProcessor, ioDispatcher))

    override suspend fun insertLocalUsers(vararg users: UserResponse): Boolean =
        localDataSource
            .insertUsers(*users.map {
                it.toDomainModel(imageProcessor, ioDispatcher) }.toTypedArray()
            )

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
       testSynchronizer.usersSync(
           ioDispatcher = ioDispatcher,
           usersFetcher = ::getRemoteUsers,
           usersPersistence = ::insertLocalUsers
       )

    suspend fun insertLocalUsers(users: List<UserResponse>) =
        insertLocalUsers(*users.toTypedArray())
}