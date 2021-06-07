package com.picpay.desafio.android.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.picpay.desafio.android.local.modal.UsersDataCache
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UsersDataCache>>

    @Transaction
    fun updateData(users: List<UsersDataCache>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<UsersDataCache>)

    @Insert
    fun insert(vararg user: UsersDataCache)

    @Query("DELETE FROM users")
    fun deleteAll()
}