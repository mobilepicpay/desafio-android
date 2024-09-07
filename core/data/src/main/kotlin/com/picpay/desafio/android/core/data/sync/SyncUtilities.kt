package com.picpay.desafio.android.core.data.sync

import android.util.Log
import com.picpay.desafio.android.core.model.ApiResponse
import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface Synchronizer {

    suspend fun getStoredUsersResponseHash(): String?

    suspend fun saveUsersResponseHash(hash: String): Boolean

    suspend fun hashUserResponseList(users: List<UserResponse>): String

    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)

}

interface Syncable {

    suspend fun syncWith(synchronizer: Synchronizer): Boolean

}

suspend fun Synchronizer.usersSync(
    ioDispatcher: CoroutineDispatcher,
    usersFetcher: suspend () -> ApiResponse<List<UserResponse>>,
    usersPersistence: suspend (List<UserResponse>) -> Boolean
): Boolean =
    withContext(ioDispatcher) {
        runCatching {
            when(val usersResponse = usersFetcher()) {
                is ApiResponse.Error -> {
                    //TODO error handling
                    usersResponse.error?.printStackTrace()
                    Log.e("APIRESPONSEERROR", "usersSync: Error code?: ${usersResponse.code}", )
                    false
                }

                is ApiResponse.Success -> {
                    val fetchedUsersHash = hashUserResponseList(usersResponse.value)

                    val dataIsUpToDate = getStoredUsersResponseHash() == fetchedUsersHash
                    if (dataIsUpToDate) return@runCatching true

                    val dataIsPersisted = awaitAll(
                        async { usersPersistence(usersResponse.value) }
                    ).all { it }

                    if (dataIsPersisted) saveUsersResponseHash(fetchedUsersHash)
                    else false
                }
            }
        }.getOrElse { exception ->
            exception.printStackTrace()
            false
        }
    }
