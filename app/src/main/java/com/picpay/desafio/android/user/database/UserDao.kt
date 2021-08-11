package com.picpay.desafio.android.user.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Insert
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE from user")
    suspend fun deleteAll()
}