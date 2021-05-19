package com.picpay.desafio.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.model.DbUser

@Dao
interface UserDao {

    @Query("SELECT * FROM USERS")
    suspend fun getAllUsers(): List<DbUser>

    @Insert(entity = DbUser::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: DbUser)
}