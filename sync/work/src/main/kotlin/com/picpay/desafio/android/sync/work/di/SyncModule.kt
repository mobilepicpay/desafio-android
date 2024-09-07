package com.picpay.desafio.android.sync.work.di

import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.sync.work.status.WorkManagerSyncManager
import com.picpay.desafio.android.sync.work.workers.SyncWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule = module {

    singleOf(::WorkManagerSyncManager) { bind<SyncManager>() }
    workerOf(::SyncWorker)

}