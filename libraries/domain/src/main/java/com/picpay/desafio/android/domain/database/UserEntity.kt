package com.picpay.desafio.android.domain.database

import androidx.room.*
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.domain.model.User

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

fun List<UserResponse>.toEntity(): List<UserEntity> {
    return map {
        UserEntity(
            id = it.id,
            name = it.name,
            username = it.username,
            img = it.img
        )
    }
}

fun List<UserEntity>.toUser(): List<User> {
    return map {
        User(
            id = it.id,
            name = it.name,
            username = it.username,
            img = it.img
        )
    }
}