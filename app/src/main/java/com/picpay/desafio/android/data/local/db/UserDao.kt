package com.picpay.desafio.android.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.local.entities.UserDb

@Dao
interface UserDao {
    @Query("SELECT * FROM USER")
    suspend fun getUser(): List<UserDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg userDb: UserDb)
}