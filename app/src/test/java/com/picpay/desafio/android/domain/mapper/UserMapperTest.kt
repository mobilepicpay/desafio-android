package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.data.entity.UserEntity
import com.picpay.desafio.android.domain.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {
    private val userEntity =
        UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1)
    private val user = User(img = "img 1", name = "name 1", username = "user name 1")

    private val listUserEntity = listOf(
        UserEntity(img = "img 1", name = "name 1", username = "user name 1", id = 1),
        UserEntity(img = "img 2", name = "name 2", username = "user name 2", id = 2)
    )
    private val listUser = listOf(
        User(img = "img 1", name = "name 1", username = "user name 1"),
        User(img = "img 2", name = "name 2", username = "user name 2")
    )

    @Test
    fun `Given UserEntity When call transformTo Should return User`() {
        val response = UserMapper.transformTo(userEntity)
        assertEquals(user, response)
    }

    @Test
    fun `Given list of UserEntity When call transformTo Should return list of  User`() {
        val response = UserMapper.transformToList(listUserEntity)
        assertEquals(listUser, response)
    }
}
