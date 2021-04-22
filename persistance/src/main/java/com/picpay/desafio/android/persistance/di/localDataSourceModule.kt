package com.picpay.desafio.android.persistance.di

import androidx.room.Room
import com.picpay.desafio.android.persistance.PicpayDatabase
import com.picpay.desafio.android.persistance.repository.UserCacheRepositoryImp
import com.picpay.desafio.android.domain.repository.UserCacheRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataSourceModule by lazy {
    module {

        single<UserCacheRepository> { UserCacheRepositoryImp(dao = get()) }

        single {
            Room.databaseBuilder(
                androidContext(),
                PicpayDatabase::class.java,
                "picpay_db"
            ).build()

        }

        single {
            get<PicpayDatabase>().userDao()
        }
    }
}

