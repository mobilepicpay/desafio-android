package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.local.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class DatabaseModuleTest {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UsersDatabase =
        Room.inMemoryDatabaseBuilder(app, UsersDatabase::class.java).allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: UsersDatabase): UserDao = db.userDao()

}