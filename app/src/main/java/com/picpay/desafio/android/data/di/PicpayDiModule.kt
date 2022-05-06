package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.core_network.retrofit.client.RetrofitClient
import com.picpay.desafio.android.data.datasource.PicpayRemoteDatasource
import com.picpay.desafio.android.data.remote.PicpayApi
import com.picpay.desafio.android.data.remote.PicpayRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal object PicpayDiModule {

    // Probably i should set the URL based on the build variant, see this later
    @Provides
    fun providePicpayApi(): PicpayApi {
        return RetrofitClient.makeService("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
    }

    @Provides
    fun providePicpayDataSource(api: PicpayApi): PicpayRemoteDatasource {
        return PicpayRemoteDataSourceImpl(api)
    }

}