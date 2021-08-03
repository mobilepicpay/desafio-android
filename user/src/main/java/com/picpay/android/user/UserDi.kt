package com.picpay.android.user

import com.picpay.android.localdb.AppDatabase
import com.picpay.android.network.createRetrofitEndPoint
import com.picpay.android.user.usedatasoucer.network.PicPayUserService
import com.picpay.android.user.usedatasoucer.network.UserNetWorkRepository
import com.picpay.android.user.presentation.UserViewModel
import com.picpay.android.user.usedatasoucer.local.UserLocalRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class UserDi(private val baseUrl: String) {

    fun getModule(): Module = module {

        single<PicPayUserService> {
            createRetrofitEndPoint(baseUrl)
        }

        single{
            UserNetWorkRepository(get())
        }

        single{
            AppDatabase.getDatabase(androidApplication()).userDao()
        }

        single {
            UserLocalRepository(get())
        }

        viewModel {
            UserViewModel(get(), get())
        }
    }
}