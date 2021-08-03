package com.picpay.android.user

import com.picpay.android.network.createRetrofitEndPoint
import com.picpay.android.user.api.PicPayUserService
import com.picpay.android.user.api.UserNetWorkRepository
import com.picpay.android.user.api.UserRepository
import com.picpay.android.user.presentation.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class UserDi(private val baseUrl: String) {

    fun getModule(): Module = module {


        single<PicPayUserService> {
            createRetrofitEndPoint(baseUrl)
        }

        single<UserRepository> {
            UserNetWorkRepository(get())
        }

        viewModel {
            UserViewModel(get())
        }

    }

}
