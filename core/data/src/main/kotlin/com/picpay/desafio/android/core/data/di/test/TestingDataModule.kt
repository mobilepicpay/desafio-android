package com.picpay.desafio.android.core.data.di.test

import com.picpay.desafio.android.core.data.image.AppImageCompressor
import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageCompressor
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageDecoder
import com.picpay.desafio.android.core.data.image.fake.createFakeImageProcessor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.data.sync.test.TestSynchronizer
import com.picpay.desafio.android.core.datastore.DataStoreRepository
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataSource
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataStore
import kotlinx.coroutines.test.StandardTestDispatcher
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val testingDataModule = module {

    single { DataSyncManager() }

    single { StandardTestDispatcher() }

    singleOf(::FakePreferencesDataSource) { bind<PreferencesDataSource>() }
    singleOf(::FakePreferencesDataStore) { bind<DataStoreRepository>() }

    singleOf(::TestSynchronizer) { bind<Synchronizer>() }

    singleOf(::TestNetworkMonitor) { bind<NetworkMonitor>() }

    singleOf(::FakeAppImageDecoder) { bind<AppImageDecoder>() }
    singleOf(::FakeAppImageCompressor) { bind<AppImageCompressor>() }

    single { createFakeImageProcessor() }.bind<AppImageProcessor>()

    singleOf(::FakeUserLocalDataSource) { bind<UserLocalDataSource>() }
    singleOf(::FakeUserRemoteDataSource) { bind<UserRemoteDataSource>() }
    singleOf(::FakeUsersRepository) { bind<UsersRepository>() }
}