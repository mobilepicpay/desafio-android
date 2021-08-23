package com.picpay.desafio.android.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.picpay.desafio.android.local.dao.GenericDAO
import com.picpay.desafio.android.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO : GenericDAO<UserEntity> {

    @Query("SELECT * FROM UserEntity")
    fun getUsers() : Flow<List<UserEntity>>

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()

    @Query("SELECT * FROM UserEntity WHERE name like :name")
    suspend fun findUsersByName(name: String): UserEntity

}