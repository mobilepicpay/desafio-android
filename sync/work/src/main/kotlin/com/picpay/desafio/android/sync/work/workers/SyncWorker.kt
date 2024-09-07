package com.picpay.desafio.android.sync.work.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.sync.Synchronizer
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import com.picpay.desafio.android.core.network.model.UserResponse
import com.picpay.desafio.android.sync.work.initializers.SyncConstraints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.time.Duration

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val ioDispatcher: CoroutineDispatcher,
    private val usersRepository: UsersRepository,
    private val preferences: PreferencesDataSource,
    private val dataSyncManager: DataSyncManager
) : CoroutineWorker(appContext, workerParams), Synchronizer {

    override suspend fun doWork(): Result = withContext(ioDispatcher) {

        val syncedSuccessfully = awaitAll(
            async { usersRepository.sync() },
        ).all { it }

        if (syncedSuccessfully) Result.success()
        else Result.retry()
    }

    override suspend fun getStoredUsersResponseHash() =
        preferences.getSyncHash()

    override suspend fun saveUsersResponseHash(hash: String) =
        preferences.saveSyncHash(hash)

    override suspend fun hashUserResponseList(users: List<UserResponse>): String =
        dataSyncManager.hashUserListWithMd5(users)

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<SyncWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofSeconds(15))
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}