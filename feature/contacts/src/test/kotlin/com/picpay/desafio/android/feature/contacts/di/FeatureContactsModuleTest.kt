package com.picpay.desafio.android.feature.contacts.di

import com.picpay.desafio.android.core.data.image.AppImageCompressor
import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.database.dao.UserDao
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCaseImpl
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCaseImpl
import com.picpay.desafio.android.core.network.services.UserServiceImpl
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import retrofit2.Retrofit

@OptIn(KoinExperimentalAPI::class)
class FeatureContactsModuleTest {

    @Test
    fun checkFeatureContactsKoinModule() {
        featureContactsModule.verify(
            extraTypes = listOf(
                Retrofit::class,
                UserServiceImpl::class,
                LocalUsersFlowUseCaseImpl::class,
                SearchLocalUsersFlowUseCaseImpl::class,
                UserLocalDataSource::class,
                UserRemoteDataSource::class,
                UsersRepository::class,
                UserDao::class,
                SyncManager::class,
                NetworkMonitor::class,
                AppImageProcessor::class,
                AppImageDecoder::class,
                AppImageCompressor::class,
                CoroutineDispatcher::class
            )
        )
    }
}