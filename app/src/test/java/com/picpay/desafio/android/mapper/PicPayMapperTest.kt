package com.picpay.desafio.android.mapper

import com.picpay.desafio.android.data.User
import com.picpay.desafio.android.data.UserEntity
import com.picpay.desafio.android.data.UserResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PicPayMapperTest {

    private val id: Int = 1
    private val name: String = "Pic Pay"
    private val username: String = "picpay"
    private val img: String = "picPay.jpg"

    private val mapper = PicPayMapperImpl()

    @Test
    fun userResponseToUser_Test() {
        val response = UserResponse(img, name, id, username)
        val users = listOf(response)

        val expectedResult = listOf(User(response.name, response.username, response.img))

        val result = runBlocking { mapper.userResponseToUser(users) }
        assert(result == expectedResult)
    }

    @Test
    fun userEntityToUser_Test() {
        val entity = UserEntity(id, name, username, img)
        val users = listOf(entity)

        val expectedResult = listOf(User(entity.name, entity.username, entity.img))

        val result = runBlocking { mapper.userEntityToUser(users) }
        assert(result == expectedResult)
    }

    @Test
    fun userResponseToUserEntity_Test() {
        val response = UserResponse(img, name, id, username)
        val users = listOf(response)

        val expectedResult =
            listOf(UserEntity(response.id, response.name, response.username, response.img))

        val result = runBlocking { mapper.userResponseToUserEntity(users) }
        assert(result == expectedResult)
    }
}
