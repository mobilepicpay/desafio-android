package com.picpay.desafio.userlist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.userlist.domain.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserListDao {
    @Insert
    fun saveList(list: List<User>)
    @Query("SELECT * FROM user")
    fun getList(): Flow<List<User>>
}