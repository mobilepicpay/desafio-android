package com.picpay.desafio.feature.contactlist.datasource.internal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("Select * from user")
    suspend fun getAllUser(): List<UserEntity>

    @Insert
    suspend fun insertAll(vararg users: UserEntity)
}