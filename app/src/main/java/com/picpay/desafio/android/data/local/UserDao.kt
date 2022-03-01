package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.models.UserDb

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDb ORDER BY id ASC")
    fun getUsers(): List<UserDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserDb)

}