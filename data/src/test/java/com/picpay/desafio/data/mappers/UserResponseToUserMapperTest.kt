package com.picpay.desafio.data.mappers

import com.picpay.desafio.data.models.UserResponse
import org.junit.Assert.*
import org.junit.Test

class UserResponseToUserMapperTest {
    private val usersList =  arrayListOf(
        UserResponse(1, "name_1", "img_1", "username_1"),
        UserResponse(2, "name_2", "img_2", "username_2"),
        UserResponse(3, "name_3", "img_3", "username_3")
    )

    private val userMapper = UserResponseToUserMapper (usersList)

    @Test
    fun getMapping() {

        val users = userMapper.getMapping()

        assertEquals(users.size, usersList.size)

        repeat(usersList.size) {
            assertEquals(users[it].id, usersList[it].id)
            assertEquals(users[it].name, usersList[it].name)
            assertEquals(users[it].img, usersList[it].img)
            assertEquals(users[it].username, usersList[it].username)
        }
    }
}