package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.UsersStub.listUsers
import com.picpay.desafio.android.UsersStub.listUsersEntity
import com.picpay.desafio.android.UsersStub.user
import com.picpay.desafio.android.UsersStub.userEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {

    @Test
    fun `Given UserEntity When call transformTo Should return User`() {
        val response = UserMapper.transformTo(userEntity)
        assertEquals(user, response)
    }

    @Test
    fun `Given list of UserEntity When call transformTo Should return list of  User`() {
        val response = UserMapper.transformToList(listUsersEntity)
        assertEquals(listUsers, response)
    }
}
