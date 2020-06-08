package com.picpay.desafio.data.db.dao

import androidx.room.*
import com.picpay.desafio.data.db.models.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(userDataList: List<UserData>)

    @Query("DELETE FROM UserData")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAllAndInsert(userDataList: List<UserData>) {
        deleteAll()
        insertList(userDataList)
    }

    @Transaction
    @Query("SELECT * FROM UserData")
    suspend fun getUsers(): List<UserData>?

    @Query("SELECT * FROM UserData WHERE id=:userId ")
    suspend fun getUser(userId: Int): UserData?
}