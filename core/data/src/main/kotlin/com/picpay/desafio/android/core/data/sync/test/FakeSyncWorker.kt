package com.picpay.desafio.android.core.data.sync.test

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.network.model.UserResponse

class FakeSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), Synchronizer {

    override suspend fun doWork(): Result =
        Result.success()

    override suspend fun getStoredUsersResponseHash(): String? =
        HASH

    override suspend fun saveUsersResponseHash(hash: String): Boolean =
        true

    override suspend fun hashUserResponseList(users: List<UserResponse>): String =
        HASH

    companion object {
        const val HASH = "HASH"
    }
}