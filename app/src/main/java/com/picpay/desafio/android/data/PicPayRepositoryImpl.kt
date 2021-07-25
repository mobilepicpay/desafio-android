package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.dto.toUsersDb
import com.picpay.desafio.android.data.source.local.LocalDataSource
import com.picpay.desafio.android.data.source.local.UserDb
import com.picpay.desafio.android.data.source.remote.NetworkDataSource
import com.picpay.desafio.android.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PicPayRepositoryImpl @Inject constructor(
    private val remoteData: NetworkDataSource,
    private val localData: LocalDataSource
) : PicPayRepository {
    override fun getUsers(): Flow<Resource<List<UserDb>>> {
        return networkBoundResource(
            query = {
                localData.getAllUsers()
            },
            fetch = {
                remoteData.allUsers().toUsersDb()
            },
            saveFetchResult = {
                localData.deleteUsers()
                localData.insertUsers(it)
            }
        )
    }
}