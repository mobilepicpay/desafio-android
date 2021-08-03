package com.picpay.desafio.android.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun save(users: List<User>)

    @Query("SELECT * FROM user")
    fun load(): Flow<List<User>>
}