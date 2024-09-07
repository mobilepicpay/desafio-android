package com.picpay.desafio.android.sync.test

import android.content.Context
import androidx.work.WorkerParameters
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import com.picpay.desafio.android.sync.work.di.syncModule
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class SyncModuleTest {

    @Test
    fun checkSyncModule() {

        syncModule.verify(
            extraTypes = listOf(
                Context::class,
                WorkerParameters::class,
                SyncManager::class,
                CoroutineDispatcher::class,
                UsersRepository::class,
                PreferencesDataSource::class,
                DataSyncManager::class
            )
        )
    }
}