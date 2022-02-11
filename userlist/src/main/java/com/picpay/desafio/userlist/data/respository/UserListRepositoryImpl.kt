package com.picpay.desafio.userlist.data.respository

import com.picpay.desafio.common.base.Resource
import com.picpay.desafio.userlist.data.dao.UserListDao
import com.picpay.desafio.userlist.data.service.PicPayService
import com.picpay.desafio.userlist.domain.model.User
import com.picpay.desafio.userlist.domain.repository.UserListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.awaitResponse
import java.security.InvalidParameterException

class UserListRepositoryImpl(
    private val service: PicPayService,
    private val dao:UserListDao
): UserListRepository {

    override suspend fun getUserList(): Flow<Resource<List<User>>> = flow {
        emit(Resource.loading<List<User>>())
        dao.getList().collect {
            if(it.isEmpty()){
                val response = service.getUsers()
                if(response.isSuccessful){
                    response.body()?.let { newList ->
                        if(dao.needUpdate(newList)){
                            dao.saveList(newList)
                            emit(Resource.success(newList))
                        }
                    } ?: emit(Resource.error<List<User>>(InvalidParameterException()))
                } else {
                    emit(Resource.error<List<User>>(Exception(response.errorBody().toString())))
                }
            } else {
                emit(Resource.success(it))
            }
        }
    }
}