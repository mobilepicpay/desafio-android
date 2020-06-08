package com.picpay.desafio.data.utils

import com.picpay.desafio.data.db.models.KeyValuePair

object DataHelper {

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