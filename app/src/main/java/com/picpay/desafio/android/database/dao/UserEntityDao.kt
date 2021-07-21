package com.picpay.desafio.android.database.dao

import androidx.room.*
import com.picpay.desafio.android.model.domain.UserEntity

@Dao
interface UserEntityDao {
    @Query("SELECT * FROM user")
    fun getUsers() : List<UserEntity>

    @Query("DELETE FROM user")
    fun deleteAllUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userList: List<UserEntity>)
}