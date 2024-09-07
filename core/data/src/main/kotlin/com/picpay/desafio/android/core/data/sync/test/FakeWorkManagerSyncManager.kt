package com.picpay.desafio.android.core.data.sync.test

import com.picpay.desafio.android.core.data.sync.SyncManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWorkManagerSyncManager(
) : SyncManager {
    override val isSyncing: Flow<Boolean>
        get() = flow { emit(false) }

}