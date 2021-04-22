package com.picpay.desafio.android.persistance.dao

import androidx.room.*
import com.picpay.desafio.android.persistance.models.UserEntity

@Dao
interface UserDao {
    @Query("DELETE FROM userentity")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM userentity")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)
}