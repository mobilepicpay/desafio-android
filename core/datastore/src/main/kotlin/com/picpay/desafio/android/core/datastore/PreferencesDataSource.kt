package com.picpay.desafio.android.core.datastore

interface PreferencesDataSource {

    suspend fun getSyncHash(): String?

    suspend fun saveSyncHash(hash: String): Boolean
}