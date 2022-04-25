package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.commons.base.baseModule
import com.picpay.desafio.android.contacts.di.contactsModule
import com.picpay.desafio.android.di.appModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class AppModuleTest : KoinTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun testModules() {
        checkKoinModules(listOf(appModule, baseModule, contactsModule))
    }
}