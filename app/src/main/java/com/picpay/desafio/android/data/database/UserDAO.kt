package com.picpay.desafio.android.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.data.model.UserEntity

@Dao
interface UserDAO {

    @Insert
    fun insert(vararg userEntity: UserEntity): List<Long>

    @Query("select * from ${UserEntity.TABLE_NAME}")
    fun getUsers(): List<UserEntity>
}
