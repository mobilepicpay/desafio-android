package com.picpay.desafio.android.local.dao

import androidx.room.Query
import com.picpay.desafio.android.local.dao.GenericDAO
import com.picpay.desafio.android.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDAO : GenericDAO<UserEntity> {

    @Query("SELECT * FROM UserEntity")
    suspend fun getUsers() : Flow<List<UserEntity>>

}