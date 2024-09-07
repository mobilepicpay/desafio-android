package com.picpay.desafio.android.core.datastore.test

import com.picpay.desafio.android.core.datastore.PreferencesDataSource

class FakePreferencesDataSource(
    private val fakePreferencesDataStore: FakePreferencesDataStore
): PreferencesDataSource {
    override suspend fun getSyncHash(): String? =
        fakePreferencesDataStore.getSyncHash()

    override suspend fun saveSyncHash(hash: String): Boolean =
        fakePreferencesDataStore.saveSyncHash(hash)
}