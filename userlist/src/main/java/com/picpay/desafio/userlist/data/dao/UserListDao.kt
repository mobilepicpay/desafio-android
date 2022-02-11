package com.picpay.desafio.userlist.data.dao

import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.Flow



interface UserListDao {
    suspend fun saveList(list: List<User>)
    suspend fun getList(): Flow<List<User>>
    suspend fun needUpdate(newList: List<User>): Boolean
}