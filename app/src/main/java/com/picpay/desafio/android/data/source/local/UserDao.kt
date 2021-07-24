package com.picpay.desafio.android.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserDb>)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

}