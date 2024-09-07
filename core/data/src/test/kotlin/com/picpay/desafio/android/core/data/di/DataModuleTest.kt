package com.picpay.desafio.android.core.data.di

import android.content.Context
import com.picpay.desafio.android.core.data.image.AppImageCompressor
import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.data.sync.test.TestSynchronizer
import com.picpay.desafio.android.core.datastore.test.FakePreferencesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import java.io.InputStream


@OptIn(KoinExperimentalAPI::class)
class DataModuleTest {

    @Test
    fun checkDataModule() {
        dataModule.verify(
            extraTypes = listOf(
                Context::class,
                SyncManager::class,
                NetworkMonitor::class,
                AppImageProcessor::class,
                AppImageDecoder::class,
                AppImageCompressor::class,
                FakePreferencesDataSource::class,
                TestSynchronizer::class,
                TestNetworkMonitor::class,
                UserLocalDataSource::class,
                UserRemoteDataSource::class,
                UsersRepository::class,
                Pair::class,
                InputStream::class,
                CoroutineDispatcher::class,
            )
        )
    }
}