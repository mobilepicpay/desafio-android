package com.picpay.desafio.android.data.datasources

import com.picpay.desafio.android.data.network.reponses.ApiResponse
import com.picpay.desafio.android.data.network.api.services.PicPayService
import com.picpay.desafio.android.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
@ExperimentalCoroutinesApi
class UserDataSource constructor(private val service: PicPayService): BaseDataSource(), UserDataSourceInterface {
    override fun getFromRemote(): Flow<ApiResponse<List<User>>> {
        return service.getUsers()
    }

    override fun getFromLocal(): Flow<List<User>?> {
        getCache(USER)?.let {
            return flowOf(it as List<User>)
        }
        return flowOf(null)
    }

    override fun saveLocal(users: List<User>?) {
        users?.let {
            setCache(USER, it)
        } ?: run {
            removeLocal()
        }
    }

    override fun removeLocal() {
        removeCache(USER)
    }
}