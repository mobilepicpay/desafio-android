package com.picpay.desafio.android.feature.home.di

import com.picpay.desafio.android.feature.home.interactor.user.DefaultUserGateway
import com.picpay.desafio.android.feature.home.interactor.user.GetKnightListUseCase
import com.picpay.desafio.android.feature.home.interactor.user.GetUserListUseCase
import com.picpay.desafio.android.feature.home.interactor.user.UserGateway
import com.picpay.desafio.android.feature.home.ui.HomeViewModel
import com.picpay.desafio.android.shared.di.KoinModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object FeatureHomeKoin : KoinModule() {

    private val gatewayModule = module {
        factory<UserGateway> { DefaultUserGateway(get(), get(), get()) }
    }

    private val useCaseModule = module {
        factory { GetUserListUseCase(get(), get()) }
        factory { GetKnightListUseCase(get(), get()) }
    }

    private val viewModelModule = module {
        viewModel { HomeViewModel(get(), get()) }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(gatewayModule + useCaseModule + viewModelModule)
        }
    }
}