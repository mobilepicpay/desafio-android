package com.picpay.android.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUsers(list: List<UserDb>)

    @Query("SELECT * FROM UserDb")
    abstract suspend fun getUsers(): List<UserDb>
}