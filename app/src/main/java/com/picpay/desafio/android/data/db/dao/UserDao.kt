package com.picpay.desafio.android.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.db.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntities: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>
}
