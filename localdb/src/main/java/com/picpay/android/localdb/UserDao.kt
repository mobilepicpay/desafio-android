package com.picpay.android.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUsers(list: List<UserDb>)

    @Query("SELECT * FROM UserDb")
    abstract fun getUsers(): List<UserDb>
}