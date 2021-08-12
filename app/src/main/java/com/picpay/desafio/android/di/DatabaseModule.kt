package com.picpay.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.picpay.desafio.android.data.db.PicPayDatabase
import com.picpay.desafio.android.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePicPayDatabase(@ApplicationContext appContext: Context): PicPayDatabase {
        return Room.databaseBuilder(
            appContext,
            PicPayDatabase::class.java,
            "picpay.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: PicPayDatabase): UserDao {
        return database.userDao()
    }
}
