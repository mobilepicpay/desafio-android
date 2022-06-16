package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.base.BaseTest
import com.picpay.desafio.android.providers.MockContactProvider
import com.picpay.desafio.android.providers.MockUserProvider
import org.junit.Test

class UserMapperTest : BaseTest() {

    @Test
    fun shouldReturnContact() {
        val contact = MockUserProvider.mockedUser().toContactModel()
        assertEquals(MockContactProvider.mockedContact(), contact)
    }

    @Test
    fun shouldReturnContacts() {
        val contacts = MockUserProvider.mockedUsers().toListContactModel()
        assertEquals(MockContactProvider.mockedContacts(), contacts)
    }
}