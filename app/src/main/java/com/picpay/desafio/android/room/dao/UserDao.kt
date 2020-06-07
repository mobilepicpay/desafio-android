package com.picpay.desafio.android.room.dao

import androidx.room.*
import com.picpay.desafio.android.custom.aliases.ListOfUsers
import com.picpay.desafio.android.room.models.User
import timber.log.Timber

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(userList: ListOfUsers)

    @Query("DELETE FROM User")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAllAndInsert(userList: ListOfUsers) {
        Timber.i("DELETING & INSERTING DATA")
        deleteAll()
        insertList(userList)
    }

    @Transaction
    @Query("SELECT * FROM User")
    suspend fun getUsers(): List<User>?

    @Query("SELECT * FROM User WHERE id=:userId ")
    suspend fun getUser(userId: Int): User?
}