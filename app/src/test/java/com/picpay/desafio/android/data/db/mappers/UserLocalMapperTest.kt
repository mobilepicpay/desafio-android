package com.picpay.desafio.android.data.db.mappers

import com.picpay.desafio.android.data.db.entities.UserEntity
import com.picpay.desafio.android.models.User
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class UserLocalMapperTest {

    private lateinit var userLocalMapper: LocalMapper<UserEntity, User>

    @Before
    fun setup() {
        userLocalMapper = UserLocalMapper()
    }

    @Test
    fun `fromEntityToModel, when an entity is passed, it is mapped to the domain type`() {
        val userEntity = UserEntity(1, "John", "image url", "John10")
        val expectedReturn = userEntity.run {
            User(id, name, image, username)
        }

        val user = userLocalMapper.fromEntityToModel(userEntity)
        assertThat(user, `is`(expectedReturn))
    }

    @Test
    fun `fromEntityToModel, when a domain type is passed, it is mapped to the entity`() {
        val user = User(1, "John", "image url", "John10")
        val expectedReturn = user.run {
            UserEntity(id, name, image, username)
        }

        val userEntity = userLocalMapper.fromModelToEntity(user)
        assertThat(userEntity, `is`(expectedReturn))
    }
}
