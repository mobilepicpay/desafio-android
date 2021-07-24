package com.picpay.desafio.android.data

import android.util.Log
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
                Log.d("ALEDEV", "query")
                localData.getAllUsers()
            },
            fetch = {
                Log.d("ALEDEV", "fetch")
                remoteData.allUsers().toUsersDb()
            },
            saveFetchResult = {
                Log.d("ALEDEV", "saveFetchResult $it")
                localData.deleteUsers()
                localData.insertUsers(it)
            }
        )
    }
}