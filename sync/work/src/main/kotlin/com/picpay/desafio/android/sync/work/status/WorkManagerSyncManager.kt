package com.picpay.desafio.android.sync.work.status

import android.content.Context
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State
import androidx.work.WorkManager
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.sync.work.initializers.SyncWorkName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map

class WorkManagerSyncManager(
    context: Context,
) : SyncManager {
    override val isSyncing: Flow<Boolean> =
        WorkManager.getInstance(context).getWorkInfosForUniqueWorkFlow(SyncWorkName)
            .map(List<WorkInfo>::anyRunning)
            .conflate()
}

private fun List<WorkInfo>.anyRunning() = any { it.state == State.RUNNING }
