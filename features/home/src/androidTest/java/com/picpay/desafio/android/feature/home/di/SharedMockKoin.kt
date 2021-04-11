package com.picpay.desafio.android.feature.home.di

import com.picpay.desafio.android.shared.coroutine.CoroutineService
import com.picpay.desafio.android.shared.coroutine.DefaultCoroutineService
import com.picpay.desafio.android.shared.data.local.PicPayLocalDataSource
import com.picpay.desafio.android.shared.data.remote.PicPayRemoteDataSource
import com.picpay.desafio.android.shared.di.KoinModule
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object SharedMockKoin : KoinModule() {

    private val retrofitModule = module {
        single<PicPayRemoteDataSource> { MockPicPayRemoteDataSource() }
    }

    private val roomModule = module {
        factory<PicPayLocalDataSource> { MockPicPayLocalDataSource() }
    }

    private val coroutineModule = module {
        single<CoroutineService> { DefaultCoroutineService() }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(retrofitModule + roomModule + coroutineModule)
        }
    }
}