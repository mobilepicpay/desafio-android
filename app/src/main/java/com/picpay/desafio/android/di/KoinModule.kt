package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.api.remote.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.network.RetrofitBuilder
import com.picpay.desafio.android.ui.contact.ContactViewModel
import com.picpay.desafio.android.ui.contact.UserListAdapter
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val challengeModule = module {

    single<PicPayService> {
        RetrofitBuilder.buildRetrofitInstance(
            OkHttpClient.Builder().build()
        ).create(PicPayService::class.java)
    }

    factory { UserListAdapter() }

    single<UserRepository> { UserRepositoryImpl(service = get()) }

    viewModel { ContactViewModel(userRepository = get()) }
}