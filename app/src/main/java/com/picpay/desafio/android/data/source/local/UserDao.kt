package com.picpay.desafio.android.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user order by name DESC")
    fun getAll(): List<UserEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<UserEntity>)

    @Delete
    fun delete(movie: UserEntity)

    @Query("DELETE FROM user")
    fun deleteAll()
}
