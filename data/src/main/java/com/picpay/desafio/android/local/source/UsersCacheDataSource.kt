package com.picpay.desafio.android.local.source

import com.picpay.desafio.android.local.database.UsersDao
import com.picpay.desafio.android.local.modal.UsersDataCache
import kotlinx.coroutines.flow.Flow

interface UsersCacheDataSource {
    fun getUsers(): Flow<List<UsersDataCache>>
    fun updateData(cacheList: List<UsersDataCache>)
}

class UsersCacheDataSourceImpl(private val usersDao: UsersDao) : UsersCacheDataSource {

    override fun getUsers(): Flow<List<UsersDataCache>> = usersDao.getUsers()

    override fun updateData(cacheList: List<UsersDataCache>) {
        usersDao.updateData(cacheList)
    }
}