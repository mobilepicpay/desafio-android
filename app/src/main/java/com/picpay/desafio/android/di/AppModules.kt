package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.database.AppDatabase
import com.picpay.desafio.android.database.dao.UserEntityDao
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.retrofit.webclient.PicPayWebClient
import com.picpay.desafio.android.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}

val daoModule = module {
    single<UserEntityDao> { get<AppDatabase>().userDao() }
}

val apiModule = module {
    single <PicPayWebClient>{ PicPayWebClient() }
}

val reposityModule = module {
    single<UserRepository> { UserRepository(get(), get())}
}

val viewModelModule = module {
    viewModel<MainActivityViewModel> { MainActivityViewModel(get()) }
}