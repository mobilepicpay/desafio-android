package com.picpay.desafio.android.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user order by id ASC")
    fun getAll(): List<UserEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<UserEntity>)

    @Query("DELETE FROM user")
    fun deleteAll()
}
