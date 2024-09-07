package com.picpay.desafio.android.core.domain.di

import android.content.Context
import com.picpay.desafio.android.core.data.repository.UsersRepository
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class DomainModuleTest {

    @Test
    fun checkDomainModule() {
        domainModule.verify(
            extraTypes = listOf(
                Context::class,
                UsersRepository::class
            )
        )
    }
}