package com.picpay.desafio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.picpay.desafio.android.data.local.model.TABLE_NAME
import com.picpay.desafio.android.data.local.model.UserLocal

@Dao
interface UserDao {
    @Query("SELECT * from $TABLE_NAME")
    suspend fun getAll(): List<UserLocal>

    @Transaction
    fun updateData(users: List<UserLocal>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<UserLocal>)

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAll()
}
