package com.picpay.desafio.android.domain.database

import androidx.room.*

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val img: String,
    val name: String,
    val username: String
)

@Dao
interface UserDao {

    @Insert
    suspend fun add(user: List<UserEntity>)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Query("DELETE from user")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(user: List<UserEntity>) {
        deleteAll()
        add(user)
    }

}