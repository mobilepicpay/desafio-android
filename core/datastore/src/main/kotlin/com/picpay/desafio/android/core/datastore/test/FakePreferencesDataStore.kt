package com.picpay.desafio.android.core.datastore.test

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.picpay.desafio.android.core.datastore.DataStoreRepository
import com.picpay.desafio.android.core.datastore.PreferencesKeys
import kotlinx.coroutines.CoroutineScope
import org.junit.rules.TemporaryFolder
import java.io.File


fun TemporaryFolder.testPreferencesDataStore(
    scope: CoroutineScope,
    producerFile: File
): DataStore<Preferences> = PreferenceDataStoreFactory.create(
    scope = scope,
    produceFile = { producerFile }
)

class FakePreferencesDataStore : DataStoreRepository {
    private val dataStore = mutableMapOf<String, String?>()

    override suspend fun getSyncHash(): String? {
        return dataStore[PreferencesKeys.SYNC_HASH.name]
    }

    override suspend fun saveSyncHash(syncHash: String): Boolean {
        dataStore[PreferencesKeys.SYNC_HASH.name] = syncHash
        return true
    }

}