package com.picpay.desafio.android.di

import com.picpay.desafio.android.core_network.retrofit.client.RetrofitClient
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.remote.PicpayApi
import com.picpay.desafio.android.data.remote.PicpayRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.PicpayRepositoryImpl
import com.picpay.desafio.android.domain.repository.PicpayRepository
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import com.picpay.desafio.android.domain.usecase.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object PicpayDiModule {

    // Probably i should set the URL based on the build variant, see this later
    @Provides
    fun providePicpayApi(): PicpayApi {
        return RetrofitClient.makeService(
            "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
        )
    }

    @Provides
    fun providePicpayDataSource(api: PicpayApi): PicpayRemoteDatasource {
        return PicpayRemoteDataSourceImpl(api)
    }

    @Provides
    fun providePicpayRepository(datasource: PicpayRemoteDatasource): PicpayRepository {
        return PicpayRepositoryImpl(datasource)
    }

    @Provides
    fun provideUserUseCase(repository: PicpayRepository): UsersInteractor {
        return UsersUseCase(repository)
    }

    @IODispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}
