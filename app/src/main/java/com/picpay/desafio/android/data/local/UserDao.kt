package com.picpay.desafio.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.models.UserResponse

@Dao
interface UserDao {

    @Query("SELECT * FROM UserResponse ORDER BY id ASC")
    fun getUsers(): List<UserResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: UserResponse)

    @Query("DELETE FROM UserResponse")
    fun clearAllUsers()

}