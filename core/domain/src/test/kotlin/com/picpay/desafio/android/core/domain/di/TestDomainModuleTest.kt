package com.picpay.desafio.android.core.domain.di

import android.content.Context
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageCompressor
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageDecoder
import com.picpay.desafio.android.core.data.image.fake.FakeAppImageProcessor
import com.picpay.desafio.android.core.data.network.test.TestNetworkMonitor
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.repository.fake.FakeUserLocalDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.fake.FakeUsersRepository
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.data.sync.test.TestSynchronizer
import com.picpay.desafio.android.core.domain.usecase.test.FakeLocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.test.FakeSearchLocalUsersFlowUseCase
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import java.io.InputStream

@OptIn(KoinExperimentalAPI::class)
class TestDomainModuleTest {

    @Test
    fun checkDomainModule() {
        testingDomainModule.verify(
            extraTypes = listOf(
                Context::class,
                UsersRepository::class,
                FakeLocalUsersFlowUseCase::class,
                FakeSearchLocalUsersFlowUseCase::class,
                SyncManager::class,
                TestSynchronizer::class,
                TestNetworkMonitor::class,
                FakeAppImageProcessor::class,
                FakeAppImageDecoder::class,
                FakeAppImageCompressor::class,
                FakeUserLocalDataSource::class,
                FakeUserRemoteDataSource::class,
                FakeUsersRepository::class,
                Pair::class,
                InputStream::class
            )
        )
    }
}