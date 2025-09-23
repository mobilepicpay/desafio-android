package com.picpay.desafio.android.di

import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.repository.PostRepositoryImpl
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.repository.PostRepository
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.GetPostsUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.presentation.posts.viewmodel.PostsViewModel
import com.picpay.desafio.android.presentation.users.viewmodel.UsersViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { OkHttpClient.Builder().build() }
    single {
        Retrofit.Builder()
            .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(PicPayService::class.java) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<PostRepository> { PostRepositoryImpl(get()) }

    factory { GetUsersUseCase(get()) }
    factory { GetPostsUseCase(get()) }

    viewModel { UsersViewModel(get()) }
    viewModel { PostsViewModel(get()) }
}
