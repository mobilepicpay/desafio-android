package com.picpay.desafio.android.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.picpay.desafio.android.core.datastore.PreferencesKeys.SYNC_HASH
import kotlinx.coroutines.flow.first

const val DATA_STORE = "app_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE)

class DesafioAppDataStoreRepository(
    private val context: Context
) : DataStoreRepository {

    override suspend fun getSyncHash(): String? =
         context.dataStore.data.first()[SYNC_HASH]

    override suspend fun saveSyncHash(syncHash: String) =
        runCatching { context.dataStore.edit { it[SYNC_HASH] = syncHash } }
            .isSuccess
}