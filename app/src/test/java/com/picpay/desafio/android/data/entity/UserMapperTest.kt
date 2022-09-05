package com.picpay.desafio.android.data.entity

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.provider.MockUserProvider
import org.junit.Test

class UserMapperTest : BaseTest() {

    @Test
    fun shouldReturnUserModel() {
        val userModel = MockUserProvider.modeckedUserDTO().toModel()
        assertEquals(MockUserProvider.mockedUser(), userModel)
    }

    @Test
    fun shouldReturnListUserModel() {
        val listUserModel = MockUserProvider.mockedUsersDTO().toModel()
        assertEquals(MockUserProvider.mockedUsers(), listUserModel)
    }
}