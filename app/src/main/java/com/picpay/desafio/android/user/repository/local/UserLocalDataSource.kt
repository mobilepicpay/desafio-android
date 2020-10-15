package com.picpay.desafio.android.user.repository.local

import androidx.room.*

@Dao
interface UserLocalDataSource {
    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

    @Transaction
    suspend fun removeLocalAndInsertAllUsers(users: List<UserEntity>) {
        deleteAllUsers()
        insertAllUsers(users)
    }
}