package com.picpay.desafio.android.datasource.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.datasource.cache.model.USER_TABLE
import com.picpay.desafio.android.datasource.cache.model.UserCM

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users:List<UserCM>)

    @Query("SELECT * FROM $USER_TABLE")
    fun getUsers(): List<UserCM>
}