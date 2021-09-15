package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.model.UserResponse
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

        val expectedResult =
            listOf(User(response.id, response.name, response.username, response.img))

        val result = runBlocking { mapper.userResponseToUser(users) }
        assert(result == expectedResult)
    }

    @Test
    fun userEntityToUser_Test() {
        val entity = UserEntity(id, name, username, img)
        val users = listOf(entity)

        val expectedResult = listOf(User(entity.id, entity.name, entity.username, entity.img))

        val result = runBlocking { mapper.userEntityToUser(users) }
        assert(result == expectedResult)
    }

    @Test
    fun userResponseToUserEntity_Test() {
        val user = User(id, name, username, img)
        val users = listOf(user)

        val expectedResult = listOf(UserEntity(user.id, user.name, user.username, user.img))

        val result = runBlocking { mapper.userToUserEntity(users) }
        assert(result == expectedResult)
    }
}
