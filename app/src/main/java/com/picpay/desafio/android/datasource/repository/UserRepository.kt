package com.picpay.desafio.android.datasource.repository

import com.picpay.desafio.android.datasource.cache.UserDAO
import com.picpay.desafio.android.datasource.mappers.toCache
import com.picpay.desafio.android.datasource.mappers.toDomain
import com.picpay.desafio.android.datasource.remote.UserRDS
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserDataRepository

class UserRepository(private val userRDS: UserRDS, private val userCDS: UserDAO) :UserDataRepository{
    override suspend fun getUsers(): List<User> {
        return try{
            val userListRM = userRDS.getUsers()
            userCDS.insertAll(userListRM.toCache())
            userListRM.toDomain()
        }catch (exception:Exception){
            val userListCM = userCDS.getUsers()
            if(userListCM.isNotEmpty()){
                userListCM.toDomain()
            }else{
                throw exception
            }
        }
    }
}