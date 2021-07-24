package com.picpay.desafio.android.di

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.source.local.UserDao
import com.picpay.desafio.android.data.source.local.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UsersDatabase =
        Room.databaseBuilder(app, UsersDatabase::class.java, "users_database")
            .build()

    @Provides
    @Singleton
    fun provideDao(db: UsersDatabase): UserDao = db.userDao()
}