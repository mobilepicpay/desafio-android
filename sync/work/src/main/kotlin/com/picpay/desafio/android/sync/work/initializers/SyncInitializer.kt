package com.picpay.desafio.android.sync.work.initializers

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.picpay.desafio.android.sync.work.workers.SyncWorker

object Sync {

    fun initialize(context: Context) {

        WorkManager.getInstance(context).apply {

            enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork(),
            )
        }
    }
}

internal const val SyncWorkName = "SyncWorkName"
