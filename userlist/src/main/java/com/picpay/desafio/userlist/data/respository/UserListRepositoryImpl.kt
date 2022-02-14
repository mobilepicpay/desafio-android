package com.picpay.desafio.userlist.data.respository

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.data.dao.UserListDao
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import com.picpay.desafio.userlist.presentation.adapter.UserListDiffCallback
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.security.InvalidParameterException

@DelicateCoroutinesApi
class UserListRepositoryImpl(
    private val service: PicPayService,
    private val dao: UserListDao
) : UserListRepository {

    override suspend fun getListFromServer(): Flow<Resource<List<User>>> = flow {
        val response = service.getUsers()
        if (response.isSuccessful) {
            response.body()?.let { newList ->
                emit(Resource.success(newList))
            } ?: throw InvalidParameterException()
        } else {
            throw InvalidParameterException()
        }
    }

    override suspend fun getListFromDatabase(): Flow<Resource<List<User>>> = flow {
        dao.getList().collect {
            if (it.isNotEmpty()) {
                emit(Resource.success(it))
            } else {
                emit(Resource.empty<List<User>>())
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun saveListFromServer(newList: List<User>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.saveList(newList)
        }
    }
}