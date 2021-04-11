package com.picpay.desafio.android.feature.home

import com.picpay.desafio.android.shared.coroutine.CoroutineDispatching
import com.picpay.desafio.android.shared.coroutine.DefaultDispatching
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
        single<CoroutineDispatching> { DefaultDispatching() }
    }

    override fun loadModule(koinApplication: KoinApplication) {
        koinApplication.apply {
            loadKoinModules(retrofitModule + roomModule + coroutineModule)
        }
    }
}