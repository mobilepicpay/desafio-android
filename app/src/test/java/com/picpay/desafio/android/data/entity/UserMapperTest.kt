package com.picpay.desafio.android.data.entity

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.provider.MockContactProvider
import org.junit.Test

class UserMapperTest : BaseTest() {

    @Test
    fun shouldReturnUserModel() {
        val userModel = MockContactProvider.mockedContactDTO().toModel()
        assertEquals(MockContactProvider.mockedContact(), userModel)
    }

    @Test
    fun shouldReturnListUserModel() {
        val listUserModel = MockContactProvider.mockedContactDTO().toModel()
        assertEquals(MockContactProvider.mockedContact(), listUserModel)
    }
}