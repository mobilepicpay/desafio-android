package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.source.local.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModuleTest {

    @Provides
    @Singleton
    @Named("test_db")
    fun provideDatabase(app: Application): UsersDatabase =
        Room.inMemoryDatabaseBuilder(app, UsersDatabase::class.java).allowMainThreadQueries()
            .build()

}