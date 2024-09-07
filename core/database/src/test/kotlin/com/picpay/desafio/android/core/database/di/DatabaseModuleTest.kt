package com.picpay.desafio.android.core.database.di

import androidx.room.RoomDatabase
import com.picpay.desafio.android.core.database.DesafioDatabase
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class DatabaseModuleTest {

    @Test
    fun checkKoinModules() {
        databaseModule.verify(
            extraTypes = listOf(
                RoomDatabase::class,
                DesafioDatabase::class
            )
        )
    }
}