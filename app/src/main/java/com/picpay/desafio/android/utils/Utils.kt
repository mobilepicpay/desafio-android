package com.picpay.desafio.android.utils

import com.picpay.desafio.android.room.models.KeyValuePair

object Utils {

    fun shouldCallApi(
        lastApiCallMillis: String,
        cacheThresholdInMillis: Long = 300000L //default value is 5 minutes//
    ): Boolean {
        return (System.currentTimeMillis() - lastApiCallMillis.toLong()) >= cacheThresholdInMillis
    }

    fun getCurrentTimeKeyValuePair(key: String): KeyValuePair {
        return KeyValuePair(key, System.currentTimeMillis().toString())
    }
}