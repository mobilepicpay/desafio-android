package com.picpay.desafio.android.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.picpay.desafio.android.coreNetwork.retrofit.client.NetworkManager
import com.picpay.desafio.android.coreNetwork.retrofit.client.RetrofitClient
import com.picpay.desafio.android.data.datasource.PicpayLocalDataSource
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.local.PicpayDataBase
import com.picpay.desafio.android.data.local.PicpayLocalDataSourceImpl
import com.picpay.desafio.android.data.remote.PicpayApi
import com.picpay.desafio.android.data.remote.PicpayRemoteRemoteDataSourceImpl
import com.picpay.desafio.android.data.repository.PicpayRepositoryImpl
import com.picpay.desafio.android.domain.repository.PicpayRepository
import com.picpay.desafio.android.domain.usecase.UsersInteractor
import com.picpay.desafio.android.domain.usecase.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

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
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PicpayDataBase {
        return Room.databaseBuilder(
            context,
            PicpayDataBase::class.java, "picpay-database"
        ).build()
    }

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("pipcay-shared", Context.MODE_PRIVATE)
    }

    @Provides
    fun providePicpayDataSource(api: PicpayApi): PicpayRemoteDatasource {
        return PicpayRemoteRemoteDataSourceImpl(api)
    }

    @Provides
    fun provideLocalPicpayDataSource(
        dataBase: PicpayDataBase,
        sharedPreferences: SharedPreferences
    ): PicpayLocalDataSource {
        return PicpayLocalDataSourceImpl(dataBase, sharedPreferences)
    }

    @Provides
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }

    @Provides
    fun providePicpayRepository(
        datasource: PicpayRemoteDatasource,
        localDataSource: PicpayLocalDataSource,
        manager: NetworkManager,
    ): PicpayRepository {
        return PicpayRepositoryImpl(datasource, localDataSource, manager)
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
