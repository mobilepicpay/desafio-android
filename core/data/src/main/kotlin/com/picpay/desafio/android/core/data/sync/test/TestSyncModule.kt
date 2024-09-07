package com.picpay.desafio.android.core.data.sync.test

import com.picpay.desafio.android.core.data.sync.SyncManager
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingSyncModule = module {

    singleOf(::FakeWorkManagerSyncManager) { bind<SyncManager>() }

    workerOf(::FakeSyncWorker)



}